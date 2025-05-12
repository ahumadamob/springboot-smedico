package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.DetalleReceta;

import java.util.List;
import java.util.Optional;

public interface DetalleRecetaService {
    List<DetalleReceta> findAll();
    Optional<DetalleReceta> findById(Long id);
    DetalleReceta save(DetalleReceta recetaMedicamento);
    DetalleReceta update(Long id, DetalleReceta recetaMedicamento);
    void deleteById(Long id);
}
