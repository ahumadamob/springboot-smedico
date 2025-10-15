package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Factura;
import java.util.List;

public interface IFacturaService {
    public List<Factura> findAll();
    public Factura create(Factura factura);
    public Factura update(Long id, Factura factura);
    public Factura findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public List<Factura> findAllByPacienteId(Long id);
    public Long countByMedioPago(String medioPago);
}
