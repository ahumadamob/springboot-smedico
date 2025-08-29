



package com.imb2025.smedico.service.jpa;

import java.util.List;

import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.DetalleFacturaRepository;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.service.IDetalleFacturaService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;



@Service
public class DetalleFacturaServiceImpl implements IDetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository repo;
    
    @Autowired
    private FacturaRepository repoFactura;

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
            throw new RuntimeException("DetalleFactura con ID " + id + " no existe.");
        }
        detalleFactura.setId(id);
        return repo.save(detalleFactura);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("DetalleFactura con ID " + id + " no existe.");
        }
        repo.deleteById(id);
    }
    
	@Override
	public DetalleFactura fromDto(DetalleFacturaRequestDto dto) throws Exception {
	    Factura factura = repoFactura.findById(dto.getFacturaId())
	    		.orElseThrow(() -> new Exception("Receta no encontrada con ID: " + dto.getFacturaId()));
		DetalleFactura detalleFactura = new DetalleFactura();
		detalleFactura.setDescripcion(dto.getDescripcion());
		detalleFactura.setImporte(dto.getImporte());
		detalleFactura.setFactura(factura);
		return detalleFactura;
	}
}

    

