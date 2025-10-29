package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.DetalleFactura;

import java.util.List;

public interface IDetalleFacturaService {

    List<DetalleFactura> findAll();

    DetalleFactura findById(Long id);

    DetalleFactura save(DetalleFactura detalleFactura);

    void deleteById(Long id);

    boolean existsById(Long id);

    List<DetalleFactura> findByDescripcion(String descripcion);

}
