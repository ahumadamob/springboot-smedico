



package com.imb2025.smedico.service.jpa;

import java.util.List;

import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.entity.DetalleFactura;

import com.imb2025.smedico.repository.DetalleFacturaRepositories;

import com.imb2025.smedico.service.IDetalleFacturaService;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



@Service
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    @Autowired
    private DetalleFacturaRepositories detalleFacturaRepositories;

    @Override
    public List<DetalleFactura> findAll() {
        return detalleFacturaRepositories.findAll();
    }

    @Override
    public DetalleFactura findById(Long id) {
        return detalleFacturaRepositories.findById(id).orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return detalleFacturaRepositories.existsById(id);
    }

    @Override
    public DetalleFactura save(DetalleFactura detalleFactura) {
        return detalleFacturaRepositories.save(detalleFactura);
    }

    @Override
    public DetalleFactura create(DetalleFactura detalleFactura) {
        return detalleFacturaRepositories.save(detalleFactura);
    }

    @Override
    public DetalleFactura update(DetalleFactura detalleFactura, Long id) {
        if (!detalleFacturaRepositories.existsById(id)) {
            throw new RuntimeException("DetalleFactura con ID " + id + " no existe.");
        }
        detalleFactura.setId(id);
        return detalleFacturaRepositories.save(detalleFactura);
    }

    @Override
    public void deleteById(Long id) {
        if (!detalleFacturaRepositories.existsById(id)) {
            throw new RuntimeException("DetalleFactura con ID " + id + " no existe.");
        }
        detalleFacturaRepositories.deleteById(id);
    }
    
    @Override
public DetalleFactura fromDto(DetalleFacturaRequestDto dto) throws Exception {
    return fromDto(dto, null);
}

@Override
public DetalleFactura fromDto(DetalleFacturaRequestDto dto, Long id) throws Exception {
    
    try {
        DetalleFactura detalleFactura = new DetalleFactura();
        if (id != null) detalleFactura.setId(id);
        detalleFactura.setDescripcion(dto.getDescripcion());
        detalleFactura.setImporte(dto.getImporte());
        

        return detalleFactura;
    } catch (Exception e) {
        throw new Exception("Error al procesar DetalleFactura: " + e.getMessage());
    }
}
}

    

