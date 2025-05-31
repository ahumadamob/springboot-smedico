package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DireccionPacienteRequestDTO;
import com.imb2025.smedico.entity.DireccionPaciente;

import java.util.List;
import java.util.Optional;

public interface DireccionPacienteService {

    List<DireccionPaciente> findAll();

    Optional<DireccionPaciente> findById(Long id);

    DireccionPaciente save(DireccionPaciente direccionPaciente);

    void deleteById(Long id);

    boolean existePorId(Long id);

    // Convierte el DTO a entidad
    DireccionPaciente fromDto(DireccionPacienteRequestDTO dto);

    // Actualiza usando el DTO
    DireccionPaciente update(Long id, DireccionPacienteRequestDTO dto);
}


