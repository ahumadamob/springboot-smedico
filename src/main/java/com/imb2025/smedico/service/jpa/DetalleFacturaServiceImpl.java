package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.DetalleFacturaRepository;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.service.IDetalleFacturaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    private final DetalleFacturaRepository repo;
    private final FacturaRepository facturaRepo;

    public DetalleFacturaServiceImpl(DetalleFacturaRepository repo, FacturaRepository facturaRepo) {
        this.repo = repo;
        this.facturaRepo = facturaRepo;
    }

    @Override
    public List<DetalleFactura> findByDescripcion(String descripcion) {
            return repo.findAll().stream()
                .filter(d -> d.getDescripcion() != null && d.getDescripcion().equals(descripcion))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("DetalleFactura no encontrado con id " + id);
        }
        repo.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public List<DetalleFactura> findAll() {
        return repo.findAll();
    }

    @Override
    public DetalleFactura findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DetalleFactura no encontrada con id " + id));
    }

    @Override
    public DetalleFactura save(DetalleFactura detalleFactura) {
        // Validar existencia de la factura asociada
        Factura factura = detalleFactura.getFactura();
        if (factura == null || factura.getId() == null) {
            throw new ResourceNotFoundException("Factura requerida o id de factura ausente");
        }
        Factura persistedFactura = facturaRepo.findById(factura.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + factura.getId()));
        detalleFactura.setFactura(persistedFactura);
        return repo.save(detalleFactura);
    }
}


