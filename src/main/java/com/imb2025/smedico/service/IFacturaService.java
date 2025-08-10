package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.FacturaRequestDto;
import com.imb2025.smedico.entity.Factura;

import java.util.List;

public interface IFacturaService {

    List<Factura> findAll();
    Factura findById(Long id);
    Factura create(Factura factura);
    Factura update(Long id, Factura factura) throws Exception;
    void deleteById(Long id) throws Exception;
    Factura fromDto(FacturaRequestDto requestDTO) throws Exception;
    boolean existsById(Long id);
}
