package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.RecetaMedicamento;

import java.util.List;
import java.util.Optional;

public interface RecetaMedicamentoService {
    List<RecetaMedicamento> findAll();
    Optional<RecetaMedicamento> findById(Long id);
    RecetaMedicamento save(RecetaMedicamento recetaMedicamento);
    RecetaMedicamento update(Long id, RecetaMedicamento recetaMedicamento);
    void deleteById(Long id);
}
