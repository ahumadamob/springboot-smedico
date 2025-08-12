package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DireccionPacienteRequestDto;
import com.imb2025.smedico.entity.DireccionPaciente;
import java.util.List;

public interface IDireccionPacienteService {
    public List<DireccionPaciente> findAll();
    public DireccionPaciente create(DireccionPaciente direccionPaciente) throws Exception;
    public DireccionPaciente update(Long id, DireccionPaciente direccionPaciente) throws Exception;
    public DireccionPaciente findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public DireccionPaciente fromDto(DireccionPacienteRequestDto direccionPacienteRequestDto) throws Exception;
}
