package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.FacturaRequestDto;
import com.imb2025.smedico.dto.response.FacturaResponseDto;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class FacturaMapper {

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedioPagoRepository medioPagoRepository;

    public Factura fromDto(FacturaRequestDto requestDTO) {
        Paciente paciente = pacienteRepository.findById(requestDTO.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró ningún paciente con el id: " + requestDTO.getPacienteId()));
        MedioPago medioPago = medioPagoRepository.findById(requestDTO.getMedioPagoId())
                .orElseThrow(() -> new ResourceNotFoundException("No se encontró ningún medio de pago con el id: " + requestDTO.getMedioPagoId()));

        Factura factura = new Factura();
        factura.setFecha(requestDTO.getFecha());
        factura.setMedioPago(medioPago);
        factura.setMonto(requestDTO.getMonto());
        factura.setPaciente(paciente);

        return factura;
    }

    public FacturaResponseDto facturaToDto(Factura factura){

        FacturaResponseDto facturaResponseDto = new FacturaResponseDto();
        facturaResponseDto.setId(factura.getId());
        facturaResponseDto.setVersion(factura.getVersion());
        facturaResponseDto.setFecha(factura.getFecha());
        facturaResponseDto.setPaciente(factura.getPaciente());
        facturaResponseDto.setMonto(factura.getMonto());
        facturaResponseDto.setMedioPago(factura.getMedioPago());

        return facturaResponseDto;
    }

}
