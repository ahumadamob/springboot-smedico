package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.exception.ResourceNotFoundException;

import java.util.List;

public interface IEstadoTurnoService {
    List<EstadoTurno> findAll();
    EstadoTurno create(EstadoTurno estadoTurno);
    EstadoTurno update(Long id, EstadoTurno estadoTurno) throws ResourceNotFoundException;
    EstadoTurno findById(Long id) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;
    EstadoTurno fromDto(EstadoTurnoRequestDto dto);
}
