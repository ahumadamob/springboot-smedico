package com.imb2025.smedico.service.jpa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.imb2025.smedico.dto.mapper.ConsultaMapper;
import com.imb2025.smedico.dto.request.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.IConsultaService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    
    private final ConsultaRepository consultaRepository;

    // 🔹 2) Constructor para que Spring inyecte el repositorio automáticamente
    public ConsultaServiceImpl(ConsultaRepository consultaRepository) {
        this.consultaRepository = consultaRepository;
    }
    @Override
    @Transactional
    public Consulta createFromDto(ConsultaRequestDto dto) {
        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Turno no encontrado con id " + dto.getTurnoId()));

        if (consultaRepository.existsByTurno_Id(dto.getTurnoId())) {
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }

        Consulta nueva = ConsultaMapper.fromDto(dto, turno);
        return consultaRepository.save(nueva);
    }
    @Override
    @Transactional
    public Consulta updateFromDto(Long id, ConsultaRequestDto dto) {
        // 1) Verificar existencia de la consulta
        Consulta existente = consultaRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consulta no encontrada con id " + id));

        // 2) Validar Turno destino
        Turno turno = turnoRepository.findById(dto.getTurnoId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Turno no encontrado con id " + dto.getTurnoId()));

        // 3) Unicidad: un Turno solo puede estar ligado a una Consulta (excepto la propia)
        if (consultaRepository.existsByTurno_IdAndIdNot(dto.getTurnoId(), id)) {
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }

        // 4) Mapear cambios (solo campos originales)
        existente.setFecha(dto.getFecha());
        existente.setDuracionMin(dto.getDuracionMin());
        existente.setComentarios(dto.getComentarios());
        existente.setTurno(turno);

        // 5) Guardar
        return consultaRepository.save(existente);
    }


    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta no encontrada con id " + id);
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Consulta> findByFechaBetween(LocalDate desde, LocalDate hasta, Pageable pageable) {
        if (desde == null || hasta == null)
            throw new IllegalArgumentException("Debe indicar las fechas 'desde' y 'hasta'");
        if (desde.isAfter(hasta))
            throw new IllegalArgumentException("'desde' no puede ser posterior a 'hasta'");

        return consultaRepository.findByFechaBetween(desde, hasta, pageable);
    }


    @Override
    @Transactional(readOnly = true)
    public long countByPacienteId(Long pacienteId) {
        if (pacienteId == null)
            throw new IllegalArgumentException("Debe indicar el id del paciente");

        return consultaRepository.countByTurno_Paciente_Id(pacienteId);
    }

}
