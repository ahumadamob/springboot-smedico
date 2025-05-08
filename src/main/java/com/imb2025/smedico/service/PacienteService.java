package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {

    List<Paciente> findAll();

    Optional<Paciente> findById(Long id);

    Paciente save(Paciente paciente);

    Paciente update(Long id, Paciente paciente);

    void deleteById(Long id);
}
