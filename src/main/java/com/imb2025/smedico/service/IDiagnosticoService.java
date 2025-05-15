package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Diagnostico;

import java.util.List;

public interface IDiagnosticoService {
    List<Diagnostico> findAll();
    Diagnostico findById(Long id);
    Diagnostico save(Diagnostico diagnostico);
    void deleteById(Long id);
}
