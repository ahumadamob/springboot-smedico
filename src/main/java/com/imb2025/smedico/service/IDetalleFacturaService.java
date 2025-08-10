

package com.imb2025.smedico.service;


import java.util.List;

import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.entity.DetalleFactura;



public interface IDetalleFacturaService {
    public List<DetalleFactura> findAll();
    public DetalleFactura findById(Long id);
    public boolean existsById(Long id);
    public DetalleFactura save(DetalleFactura detalleFactura);
    public DetalleFactura create(DetalleFactura detalleFactura);
    public DetalleFactura update(Long id, DetalleFactura detalleFactura);
    public void deleteById(Long id);
    public DetalleFactura fromDto(DetalleFacturaRequestDto detalleFacturaRequestDto) throws Exception;
    public DetalleFactura fromDto(DetalleFacturaRequestDto detalleFacturaRequestDto, Long id) throws Exception;
}
                    