package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DetalleRecetaRequestDTO;
import com.imb2025.smedico.entity.DetalleReceta;

import java.util.List;

public interface IDetalleRecetaService {
    List<DetalleReceta> findAll();
    DetalleReceta findById(Long id);
    DetalleReceta save(DetalleReceta recetaMedicamento);
    void deleteById(Long id);

    // Métodos nuevos con el DTO
    DetalleReceta saveFromDTO(DetalleRecetaRequestDTO dto);
    DetalleReceta updateFromDTO(Long id, DetalleRecetaRequestDTO dto);
}
