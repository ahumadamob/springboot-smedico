package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Diagnostico;

import java.util.List;
import java.util.Optional;

public interface IDiagnosticoService {
    List<Diagnostico> findAll();
    Optional<Diagnostico> findById(Long id);
    Diagnostico save(Diagnostico diagnostico);
    Diagnostico update(Long id, Diagnostico diagnostico);
    void deleteById(Long id);
}
