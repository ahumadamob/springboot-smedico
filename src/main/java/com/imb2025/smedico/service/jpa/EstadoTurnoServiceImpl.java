package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.exception.ResourceNotFoundException;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.service.IEstadoTurnoService;

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
        return estadoTurnoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "EstadoTurno no encontrado con id " + id));
    }

    @Override
    public EstadoTurno create(EstadoTurno estadoTurno) {
        return estadoTurnoRepository.save(estadoTurno);
    }

    @Override
    public EstadoTurno update(Long id, EstadoTurno estadoTurno) throws ResourceNotFoundException {
        EstadoTurno existente = findById(id); // Reutilizamos findById, que ya maneja la excepción
        existente.setNombre(estadoTurno.getNombre());
        return estadoTurnoRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) throws ResourceNotFoundException {
        if (!estadoTurnoRepository.existsById(id)) {
            throw new ResourceNotFoundException("EstadoTurno no encontrado con id " + id);
        }
        estadoTurnoRepository.deleteById(id);
    }

    @Override
    public EstadoTurno fromDto(EstadoTurnoRequestDto dto) {
        EstadoTurno estadoTurno = new EstadoTurno();
        estadoTurno.setNombre(dto.getNombre());
        return estadoTurno;
    }
}
