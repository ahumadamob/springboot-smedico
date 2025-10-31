package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IFacturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FacturaServiceImpl implements IFacturaService {

    @Autowired
    private FacturaRepository facturaRepository;


    @Override
    public List<Factura> findAll() {
        return facturaRepository.findAll();
    }

    @Override
    public Factura findById(Long id) {
        return facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Entidad no encontraba con id: " + id
                ));
    }

    @Override
    public Factura create(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(Long id, Factura factura){
        if (this.existsById(id)) {
            factura.setId(id);
            return facturaRepository.save(factura);
        }
        throw new ResourceNotFoundException("Factura con ID: " + id + " no encontrada");
    }

    @Override
    public void deleteById(Long id) {
        Factura existente = facturaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Factura con ID: " + id + " no encontrada"));
        facturaRepository.deleteById(id);
    }

    @Override
    public boolean existsById(Long id) {
        return facturaRepository.existsById(id);
    }

    @Override
    public List<Factura> findAllByPacienteId(Long id){
        return facturaRepository.findAllByPacienteId(id);
    }

    @Override
    public Long countByMedioPago(String medioPago) {
        return facturaRepository.countByMedioPagoNombreIgnoreCase(medioPago);
    }

}
