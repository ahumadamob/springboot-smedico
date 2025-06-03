package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Factura;

import java.util.List;

public interface IFacturaService {

    List<Factura> findAll();
    Factura findById(Long id);
    Factura save(Factura factura);
    void deleteById(Long id);

}
