package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.EstadoTurnoDTO;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.service.IEstadoTurnoService;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class EstadoTurnoServiceImpl implements IEstadoTurnoService {

    private final EstadoTurnoRepository estadoTurnoRepository;

    public EstadoTurnoServiceImpl(EstadoTurnoRepository estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    @Override
    public List<EstadoTurno> findAll() {
        return estadoTurnoRepository.findAll();
    }

    @Override
    public EstadoTurno findById(Long id) {
        return estadoTurnoRepository.findById(id).orElse(null);
    }

    @Override
    public EstadoTurno create(EstadoTurno estadoTurno) {
        return estadoTurnoRepository.save(estadoTurno);
    }

    @Override
    public EstadoTurno update(EstadoTurno estadoTurno, Long id) {
        Optional<EstadoTurno> existente = estadoTurnoRepository.findById(id);
        if (existente.isPresent()) {
            EstadoTurno actualizado = existente.get();
            actualizado.setNombre(estadoTurno.getNombre());
            return estadoTurnoRepository.save(actualizado);
        }
        throw new RuntimeException("EstadoTurno con id " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        estadoTurnoRepository.deleteById(id);
    }

    @Override
    public EstadoTurno fromDto(EstadoTurnoDTO dto) {
        EstadoTurno estadoTurno = new EstadoTurno();
        estadoTurno.setNombre(dto.getNombre());
        return estadoTurno;
    }
}
