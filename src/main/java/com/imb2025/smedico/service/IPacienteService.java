package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.PacienteRequestDto;
import com.imb2025.smedico.entity.Paciente;
import java.util.List;

public interface IPacienteService {
    public List<Paciente> findAll();
    public List<Paciente> findAllOrder();
    public List<Paciente> findByDni(String dni);
    public List<Paciente> findByDomainEmail(String domain);
    public Long countBy();
    public Paciente create(Paciente paciente);
    public Paciente update(Long id, Paciente paciente);
    public Paciente findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public List<Paciente> findActivos();
    public List<Paciente> findInactivos();
}
