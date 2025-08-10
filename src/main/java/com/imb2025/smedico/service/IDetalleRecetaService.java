package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;

import java.util.List;

public interface IDetalleRecetaService {
    List<DetalleReceta> findAll();
    DetalleReceta findById(Long id);
    DetalleReceta save(DetalleReceta recetaMedicamento);
    void deleteById(Long id);

    // Métodos nuevos con el DTO
    DetalleReceta saveFromDTO(DetalleRecetaRequestDto dto);
    DetalleReceta updateFromDTO(Long id, DetalleRecetaRequestDto dto);
}
