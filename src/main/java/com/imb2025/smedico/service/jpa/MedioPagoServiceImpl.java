package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.dto.MedioPagoRequestDTO;
import com.imb2025.smedico.entity.MedioPago;
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
	public MedioPago update(Long id, MedioPago medioPago) {
		if (repo.existsById(id)) {
			medioPago.setId(id);
            return repo.save(medioPago);
        } else {
            return null;
        }
	}
	
	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
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
}
