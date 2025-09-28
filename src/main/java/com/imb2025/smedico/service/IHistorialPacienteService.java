package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.HistorialPacienteRequestDto;
import com.imb2025.smedico.entity.HistorialPaciente;
import java.util.List;

public interface IHistorialPacienteService {
    public List<HistorialPaciente> findAll();
    public HistorialPaciente create(HistorialPaciente historialPaciente);
    public HistorialPaciente update(Long id, HistorialPaciente historialPaciente);
    public HistorialPaciente findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public HistorialPaciente fromDto(HistorialPacienteRequestDto historialPacienteRequestDto);
}
