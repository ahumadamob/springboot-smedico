package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.DetalleFacturaRequestDto;
import com.imb2025.smedico.dto.response.DetalleFacturaResponseDto;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.entity.Factura;

import java.math.BigDecimal;

public class DetalleFacturaMapper {

    public static DetalleFactura fromDto(DetalleFacturaRequestDto dto, Factura factura) {
        DetalleFactura entidad = new DetalleFactura();
        entidad.setDescripcion(dto.getDescripcion());
        entidad.setImporte(dto.getImporte() != null ? BigDecimal.valueOf(dto.getImporte()) : null);
        entidad.setFactura(factura);
        return entidad;
    }

    public static DetalleFacturaResponseDto toResponseDto(DetalleFactura entidad) {
        return new DetalleFacturaResponseDto(
            entidad.getId(),
            entidad.getDescripcion(),
            entidad.getImporte(),
            entidad.getFactura() != null ? entidad.getFactura().getId() : null,
            entidad.getVersion()
        );
    }
}
