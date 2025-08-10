package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DireccionPacienteRequestDto;
import com.imb2025.smedico.entity.DireccionPaciente;
import java.util.List;

public interface IDireccionPacienteService {

    List<DireccionPaciente> findAll();
    DireccionPaciente findById(Long id);
    DireccionPaciente create(DireccionPaciente direccionPaciente) throws Exception;
    DireccionPaciente update(Long id, DireccionPaciente direccionPaciente) throws Exception;
    void deleteById(Long id);
    DireccionPaciente fromDto(DireccionPacienteRequestDto dto) throws Exception;
    boolean existsById(Long id);
}
