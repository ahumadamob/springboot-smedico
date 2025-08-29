package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.dto.MedioPagoRequestDTO;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;
import com.imb2025.smedico.service.IMedioPagoService;

@Service
public class MedioPagoServiceImpl implements IMedioPagoService {
	@Autowired
	private MedioPagoRepository repo;
	@Autowired
	private FacturaRepository facturaRepository;
	
	@Override
	public List<MedioPago> findAll() {
		return repo.findAll();
	}
	
	
	@Override
	public MedioPago findById(Long id) {
		Optional<MedioPago> opt;
		opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}

	@Override
	public MedioPago save(MedioPago medioPago) {
		return repo.save(medioPago);
	}
	
	@Override
	public MedioPago update(Long id, MedioPago medioPago) throws Exception{
		if (this.existsById(id)) {
			medioPago.setId(id);
            return repo.save(medioPago);
        } else {
        	throw new Exception("Medio de pago de ID " + id + " no encontrado.");
        }
	}
	
	@Override
	public void deleteById(Long id) throws Exception{
		if (this.existsById(id)){
			repo.deleteById(id);
		}else {
			throw new Exception("Registro de mediopago no encontrado");
		}
	}
	/*
	//Capturar excepciones sin afectar el flujo de ejecuciones
	@Override
	public MedioPago createMedioPago (MedioPagoRequestDTO dto) { 		
		try {
            MedioPago medioPago = new MedioPago();
            medioPago.setNombre(dto.getNombre());
            medioPago.setTipo(dto.getTipo());

            return repo.save(medioPago);
		} catch (Exception e) {
	        System.out.println("Error al crear MedioPago: " + e.getMessage());
	        return null; 
	    }
	}
	*/
	@Override
	public MedioPago fromDto(MedioPagoRequestDTO dto) { 		
		if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("Complete el campo nombre");
        }
		if (dto.getTipo() == null || dto.getTipo().isBlank()) {
	        throw new IllegalArgumentException("Coloque el tipo/categoria de pago");
	    }

		String tipoIngresado = dto.getTipo().toUpperCase();
		if (!tipoIngresado.equals("TARJETA_CREDITO_DEBITO") &&
				!tipoIngresado.equals("MERCADO_PAGO") &&
		        !tipoIngresado.equals("CANJE_CUPON")) {
			throw new IllegalArgumentException("Tipo de pago inválido. Use: TARJETA_CREDITO_DEBITO, MERCADO_PAGO o CANJE_CUPON");
		}

		TipoPago tipo = TipoPago.valueOf(tipoIngresado);

        MedioPago medioPago = new MedioPago();
        medioPago.setNombre(dto.getNombre());
        medioPago.setTipo(tipo);

        return repo.save(medioPago);
	}
	
	@Override
	public boolean existsById(Long id) {
	    return repo.existsById(id);
	}

	
	/*@Override
	public MedioPago fromDto(MedioPagoRequestDTO dto) throws Exception { 		
		Factura f= FacturaRepository.findById(dto.getFacturaId());
			.orElseThrow(() -> new Exception("Factura no encontrada"));
		return new MedioPago(dto.getNombre(), dto.getTipo(), f);
	}*/
}
