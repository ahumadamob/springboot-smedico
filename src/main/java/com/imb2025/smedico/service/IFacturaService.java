package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.FacturaRequestDto;
import com.imb2025.smedico.entity.Factura;
import java.util.List;

public interface IFacturaService {
    public List<Factura> findAll();
    public Factura create(Factura factura);
    public Factura update(Long id, Factura factura) throws Exception;
    public Factura findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Factura fromDto(FacturaRequestDto facturaRequestDto) throws Exception;
}
