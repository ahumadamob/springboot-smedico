package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import java.util.List;

public interface IEstadoTurnoService {
    public List<EstadoTurno> findAll();
    public EstadoTurno create(EstadoTurno estadoTurno);
    public EstadoTurno update(Long id, EstadoTurno estadoTurno);
    public EstadoTurno findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public EstadoTurno fromDto(EstadoTurnoRequestDto estadoTurnoRequestDto);
}
