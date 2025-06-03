package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImp implements IFacturaService {

    @Autowired
    FacturaRepository facturaRepository;

    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        return factura.orElse(null);
    }

    @Override
    public Factura save(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }
}
