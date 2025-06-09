package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.PacienteRequestDTO;
import com.imb2025.smedico.entity.Paciente;

import java.util.List;

public interface IPacienteService {
    List<Paciente> findAll();
    Paciente findById(Long id);
    boolean existsById(Long id);
    Paciente save(Paciente paciente);
    Paciente create(Paciente paciente);
    Paciente update(Long id, Paciente paciente) throws Exception;
    void deleteById(Long id);
    Paciente fromDto(PacienteRequestDTO requestDTO);
}
