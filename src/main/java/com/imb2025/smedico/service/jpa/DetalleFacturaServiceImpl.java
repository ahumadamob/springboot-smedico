package com.imb2025.smedico.service.jpa;

import java.util.List;

import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.DetalleFacturaRepository;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.service.IDetalleFacturaService;
import java.math.BigDecimal;

import org.springframework.stereotype.Service;

@Service
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    private final DetalleFacturaRepository repo;
    private final FacturaRepository repoFactura;

    // ✅ Inyección por constructor (más claro y seguro)
    public DetalleFacturaServiceImpl(DetalleFacturaRepository repo, FacturaRepository repoFactura) {
        this.repo = repo;
        this.repoFactura = repoFactura;
    }

    @Override
    public List<DetalleFactura> findAll() {
        return repo.findAll();
    }

    @Override
    public DetalleFactura findById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "DetalleFactura no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public DetalleFactura create(DetalleFactura detalleFactura) {
        return repo.save(detalleFactura);
    }

    @Override
    public DetalleFactura update(Long id, DetalleFactura detalleFactura) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("DetalleFactura no encontrada con id " + id);
        }
        detalleFactura.setId(id);
        return repo.save(detalleFactura);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("DetalleFactura no encontrada con id " + id);
        }
        repo.deleteById(id);
    }
    
    @Override
    public DetalleFactura fromDto(DetalleFacturaRequestDto dto) {
        Factura factura = repoFactura.findById(dto.getFacturaId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Factura no encontrada con id " + dto.getFacturaId()));

        DetalleFactura detalleFactura = new DetalleFactura();
        detalleFactura.setDescripcion(dto.getDescripcion());
        detalleFactura.setImporte(BigDecimal.valueOf(dto.getImporte()));
        detalleFactura.setFactura(factura);

        return detalleFactura;
    }

    @Override
    public List<DetalleFactura> findByDescripcion(String descripcion) {
        return repo.findByDescripcionContainingIgnoreCase(descripcion);
    }

    @Override
    public long countByFacturaId(Long facturaId) {
        return repo.countByFactura_Id(facturaId);
    }
}


