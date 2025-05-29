



package com.imb2025.smedico.service.jpa;

import java.util.List;

import com.imb2025.smedico.dto.DetalleFacturaRequestDTO;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.repository.DetalleFacturaRepositories;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.service.IDetalleFacturaService;

import org.springframework.stereotype.Service;



@Service
public class DetalleFacturaServiceimpl implements IDetalleFacturaService {

    private final DetalleFacturaRepositories detalleFacturaRepositories;
    private final FacturaRepository facturaRepository;
    public DetalleFacturaServiceimpl(
    DetalleFacturaRepositories detalleFacturaRepositories,
    FacturaRepository facturaRepository
) {
    this.detalleFacturaRepositories = detalleFacturaRepositories;
    this.facturaRepository = facturaRepository;
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
    
    @Override
public DetalleFactura fromDto(DetalleFacturaRequestDTO dto) throws Exception {
    return fromDto(dto, null);
}

@Override
public DetalleFactura fromDto(DetalleFacturaRequestDTO dto, Long id) throws Exception {
    try {
        Factura factura = facturaRepository.findById(dto.getFacturaId())
            .orElseThrow(() -> new Exception("Factura con ID " + dto.getFacturaId() + " no encontrada."));

        DetalleFactura detalleFactura = new DetalleFactura();
        if (id != null) detalleFactura.setId(id);
        detalleFactura.setDescripcion(dto.getDescripcion());
        detalleFactura.setImporte(dto.getImporte());
        detalleFactura.setFactura(factura);

        return detalleFactura;
    } catch (Exception e) {
        throw new Exception("Error al procesar DetalleFactura: " + e.getMessage());
    }
}

    
}
