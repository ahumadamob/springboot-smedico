package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;

import java.util.List;

public interface IDetalleRecetaService {

    /** Listar todas las DetalleRecetas */
    List<DetalleReceta> findAll();

    /** Crear nueva DetalleReceta */
    DetalleReceta create(DetalleReceta detalleReceta);

    /** Actualizar DetalleReceta existente */
    DetalleReceta update(Long id, DetalleReceta detalleReceta);

    /** Buscar DetalleReceta por ID */
    DetalleReceta findById(Long id);

    /** Verificar existencia de DetalleReceta por ID */
    boolean existsById(Long id);

    /** Eliminar DetalleReceta por ID */
    void deleteById(Long id);

    /** Convertir DTO a entidad */
    DetalleReceta convertFromDto(DetalleRecetaRequestDto detalleRecetaRequestDto);
}
