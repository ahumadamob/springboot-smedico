



package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.repositories.DetalleFacturaRepositories;
import org.springframework.stereotype.Service;



@Service
public class DetalleFacturaServiceimpl implements IDetalleFacturaService {

    private final DetalleFacturaRepositories detalleFacturaRepositories;

    public DetalleFacturaServiceimpl(DetalleFacturaRepositories detalleFacturaRepositories) {
        this.detalleFacturaRepositories = detalleFacturaRepositories;
    }

    @Override
    public List<DetalleFactura> findAll() {
        
        return detalleFacturaRepositories.findAll();
    }

    @Override
    public DetalleFactura findById(Long id) {
        DetalleFactura detalleFactura = detalleFacturaRepositories.findById(id).orElse(null);
        return detalleFactura;
    }

    @Override
    public DetalleFactura save(DetalleFactura detalleFactura) {

        return detalleFacturaRepositories.save(detalleFactura);
    }

    @Override
    public void deleteById(Long id) {

        
        detalleFacturaRepositories.deleteById(id);
    }
    
}
