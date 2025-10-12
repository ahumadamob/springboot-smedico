package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.entity.DetalleFactura;
import java.util.List;

public interface IDetalleFacturaService {
    List<DetalleFactura> findAll();
    DetalleFactura create(DetalleFactura detalleFactura);
    DetalleFactura update(Long id, DetalleFactura detalleFactura);
    DetalleFactura findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    DetalleFactura fromDto(DetalleFacturaRequestDto dto);
}
