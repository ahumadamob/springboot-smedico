package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.PacienteRequestDto;
import com.imb2025.smedico.entity.Paciente;
import java.util.List;

public interface IPacienteService {
    public List<Paciente> findAll();
    public Paciente create(Paciente paciente);
    public Paciente update(Long id, Paciente paciente);
    public Paciente findById(Long id);
    public void deleteById(Long id);
    public Paciente fromDto(PacienteRequestDto pacienteRequestDto);
}
