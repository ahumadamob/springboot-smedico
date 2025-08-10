package com.imb2025.smedico.service;

//import com.imb2025.smedico.dto.DireccionPacienteRequestDto;
import com.imb2025.smedico.entity.DireccionPaciente;

import java.util.List;
import java.util.Optional;

public interface DireccionPacienteService {

    List<DireccionPaciente> findAll();

    Optional<DireccionPaciente> findById(Long id);

    boolean existePorId(Long id);

    // Convierte el DTO a entidad
    //DireccionPaciente fromDto(DireccionPacienteRequestDto dto);
    //DireccionPaciente update(Long id, DireccionPacienteRequestDto dto);

    DireccionPaciente save(DireccionPaciente direccionPaciente);

    DireccionPaciente update(Long id, DireccionPaciente direccionPaciente);

    void deleteById(Long id);
}


