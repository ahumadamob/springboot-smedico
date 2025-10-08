package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.entity.HabitacionPaciente;

import java.util.List;

public interface HabitacionPacienteService {

    List<HabitacionPaciente> findAll();

    HabitacionPaciente findById(Long id);

    HabitacionPaciente save(HabitacionPaciente habitacionPaciente);

    HabitacionPaciente update(Long id, HabitacionPacienteRequestDTO dto);

    void deleteById(Long id);

    boolean existePorId(Long id);

    HabitacionPaciente fromDto(HabitacionPacienteRequestDTO dto);
}

