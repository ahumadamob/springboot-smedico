package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.FacturaRequestDTO;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaServiceImp implements IFacturaService {

    @Autowired
    FacturaRepository facturaRepository;
    @Autowired
    PacienteRepository pacienteRepository;
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
        if(this.existsById(id)){
            factura.setId(id);
            return facturaRepository.save(factura);
        } else {
            throw new Exception("Factura no encontrada");
        }
    }

    @Override
    public void deleteById(Long id) throws Exception{
        if (this.existsById(id)){
            facturaRepository.deleteById(id);
        } else{
            throw new Exception("Factura no encontrada");
        }
    }

    @Override
    public Factura fromDto(FacturaRequestDTO requestDTO) throws Exception{
        Paciente paciente = pacienteRepository.findById(requestDTO.getPacienteId())
                .orElseThrow(() -> new Exception("No se encontró ningún paciente con el id: " + requestDTO.getPacienteId()));
        MedioPago medioPago = medioPagoRepository.findById(requestDTO.getMedioPagoId())
                .orElseThrow(() -> new Exception("No se encontró ningún medio de pago con el id: " + requestDTO.getMedioPagoId()));
        return new Factura(requestDTO.getFecha(), paciente, requestDTO.getMonto(), medioPago);
    }

    @Override
    public boolean existsById(Long id) {
        return facturaRepository.existsById(id);
    }
}
