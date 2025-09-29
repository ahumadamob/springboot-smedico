package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.IConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public List<Consulta> findAll() {
        return repository.findAll();
    }

    @Override
    public Consulta findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }
//.
    @Override   
    @Transactional
    public Consulta createFromDto(ConsultaRequestDto dto) {
        // 1) Validar Turno existente
        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id " + dto.getTurnoId()));

        // 2) Regla: un Turno solo puede estar asociado a una Consulta
        if (repository.existsByTurno_Id(dto.getTurnoId())) {
            // Podés mapear esta excepción a 422 en tu GlobalExceptionHandler
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }

        // 3) Mapear y persistir
        Consulta c = new Consulta();
        c.setFecha(dto.getFecha());
        c.setTurno(turno);
        c.setDuracionMin(dto.getDuracionMin());
        c.setComentarios(dto.getComentarios());

        return repository.save(c);
    }
    @Override
    @Transactional
    public Consulta updateFromDto(Long id, ConsultaRequestDto dto) {
        // 1) Existe la consulta
        Consulta existente = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id " + id));

        // 2) Validar Turno
        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id " + dto.getTurnoId()));

        // 3) Unicidad de turno (permitir si es la misma consulta)
        if (repository.existsByTurno_IdAndIdNot(dto.getTurnoId(), id)) {
            // tu consigna usa 409 para conflictos:
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }

        // 4) Mapear cambios
        existente.setFecha(dto.getFecha());
        existente.setDuracionMin(dto.getDuracionMin());
        existente.setComentarios(dto.getComentarios());
        existente.setTurno(turno);

        // 5) Persistir
        return repository.save(existente);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta no encontrada con id " + id);
        }
        repository.deleteById(id);
    }
}
