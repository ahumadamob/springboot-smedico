package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.entity.DetalleFactura;
import java.util.List;

public interface IDetalleFacturaService {
    public List<DetalleFactura> findAll();
    public DetalleFactura create(DetalleFactura detalleFactura);
    public DetalleFactura update(Long id, DetalleFactura detalleFactura);
    public DetalleFactura findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public DetalleFactura fromDto(DetalleFacturaRequestDto dto) throws Exception;
}
