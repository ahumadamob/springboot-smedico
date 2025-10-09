package com.imb2025.smedico.dto.mapper;

import com.imb2025.smedico.dto.request.DetalleRecetaRequestDto;
import com.imb2025.smedico.dto.response.DetalleRecetaResponseDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.entity.Medicamento;

public class DetalleRecetaMapper {

    // Convierte un DTO de request en entidad
    public static DetalleReceta fromDto(DetalleRecetaRequestDto dto) {
        DetalleReceta detalle = new DetalleReceta();

        // Crear objetos Receta y Medicamento solo con ID
        Receta receta = new Receta();
        receta.setId(dto.getRecetaId());

        Medicamento medicamento = new Medicamento();
        medicamento.setId(dto.getMedicamentoId());

        detalle.setReceta(receta);
        detalle.setMedicamento(medicamento);
        detalle.setDosis(dto.getDosis());
        detalle.setFrecuencia(dto.getFrecuencia());

        return detalle;
    }

    // Convierte una entidad en DTO de response
    public static DetalleRecetaResponseDto toResponseDto(DetalleReceta detalle) {
        DetalleRecetaResponseDto dto = new DetalleRecetaResponseDto();
        dto.setId(detalle.getId());
        dto.setRecetaId(detalle.getReceta().getId());
        dto.setMedicamentoId(detalle.getMedicamento().getId());
        dto.setDosis(detalle.getDosis());
        dto.setFrecuencia(detalle.getFrecuencia());
        dto.setVersion(detalle.getVersion());
        return dto;
    }
}


