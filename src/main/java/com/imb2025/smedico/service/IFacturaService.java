package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.FacturaRequestDTO;
import com.imb2025.smedico.entity.Factura;

import java.util.List;

public interface IFacturaService {

    List<Factura> findAll();
    Factura findById(Long id);
    Factura create(Factura factura);
    Factura update(Long id, Factura factura) throws Exception;
    void deleteById(Long id);
    Factura fromDto(FacturaRequestDTO requestDTO) throws Exception;
    
    // Agregado: Declaración de createFactura()
    Factura createFactura(FacturaRequestDTO dto) throws Exception;
}
