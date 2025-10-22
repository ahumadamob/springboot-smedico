package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.DetalleRecetaRequestDto;
import com.imb2025.smedico.dto.response.DetalleRecetaResponseDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.entity.Medicamento;

public class DetalleRecetaMapper {


    public static DetalleReceta fromDto(DetalleRecetaRequestDto dto) {
        DetalleReceta detalle = new DetalleReceta();

      
        Receta receta = new Receta();
        receta.setId(dto.getRecetaId());

        Medicamento medicamento = new Medicamento();
        medicamento.setId(dto.getMedicamentoId());

        detalle.setReceta(receta);
        detalle.setMedicamento(medicamento);
        detalle.setDosis(dto.getDosis());
        detalle.setFrecuencia(dto.getFrecuencia());

       
        detalle.setControlado(dto.isControlado());

        return detalle;
    }

   
    public static DetalleRecetaResponseDto toResponseDto(DetalleReceta detalle) {
        DetalleRecetaResponseDto dto = new DetalleRecetaResponseDto();
        dto.setId(detalle.getId());
        dto.setReceta(detalle.getReceta());
        dto.setMedicamento(detalle.getMedicamento());
        dto.setDosis(detalle.getDosis());
        dto.setFrecuencia(detalle.getFrecuencia());
        dto.setVersion(detalle.getVersion());

       
        dto.setControlado(detalle.isControlado());

        return dto;
    }
}



