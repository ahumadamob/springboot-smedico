package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Paciente;

import java.util.List;

public interface IPacienteService {

    List<Paciente> findAll();
    Paciente findById(Long id);
    Paciente save(Paciente paciente);
     void deleteById(Long id);

}