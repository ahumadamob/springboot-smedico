package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.FacturaRequestDTO;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.MedioPago;
import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImp implements IFacturaService {

    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    MedioPagoRepository medioPagoRepository;

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
    public Factura create(Factura factura) {
        return facturaRepository.save(factura);
    }

    @Override
    public Factura update(Long id, Factura factura) throws Exception{
        if(facturaRepository.existsById(id)){
            factura.setId(id);
            return facturaRepository.save(factura);
        } else {
            throw new Exception("Factura no encontrada");
        }
    }

    @Override
    public void deleteById(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    public Factura fromDto(FacturaRequestDTO requestDTO) throws Exception{
       
        MedioPago medioPago = medioPagoRepository.findById(requestDTO.getMedioPagoId()) //Agregado
                .orElseThrow(() -> new Exception("No se encontró el medio de pago con ID: " + requestDTO.getMedioPagoId()));
        return new Factura(requestDTO.getFecha(), requestDTO.getMonto(), medioPago); //Modificado: retorno de entidad medio pago
    }
    
    //Agregado: Manejo de errores con try-catch
    @Override
    public Factura createFactura(FacturaRequestDTO dto) { //Me susbraya en rojo createFactura(FacturaRequestDTO dto) .The method createFactura(FacturaRequestDTO) of type FacturaServiceImp must override or implement a supertype method 
        try {
            return facturaRepository.save(fromDto(dto));
        } catch (Exception e) {
            System.out.println("Error al crear Factura: " + e.getMessage());
            return null; // Evitando `ResponseEntity`
        }
    }
    
}
