package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.MotivoCancelacionRequestDTO;
import com.imb2025.smedico.entity.MotivoCancelacion;
import com.imb2025.smedico.repository.MotivoCancelacionRepository;
import com.imb2025.smedico.service.IMotivoCancelacionService;

@Service
public class MotivoCancelacionImpl implements IMotivoCancelacionService{

	@Autowired
	private MotivoCancelacionRepository repo;

	@Override
	public List<MotivoCancelacion> findAll() {
		return repo.findAll();
	}

	@Override
	public MotivoCancelacion findById(Long id) {
		Optional<MotivoCancelacion> opt;
		opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public MotivoCancelacion create(MotivoCancelacion motivoCancelacion) {
	    return repo.save(motivoCancelacion);
	}

	@Override
	public MotivoCancelacion update(Long id, MotivoCancelacion motivoCancelacion) throws Exception {
	    if (repo.existsById(id)) {
	        motivoCancelacion.setId(id);
	        return repo.save(motivoCancelacion);
	    } else {
	        throw new Exception("MotivoCancelacion con ID " + id + " no existe.");
	    }
	}

	@Override
	public MotivoCancelacion fromDto(MotivoCancelacionRequestDTO dto) {
	    if (dto.getNombre() == null || dto.getNombre().isBlank()) {
	        throw new IllegalArgumentException("El nombre no puede estar nulo o vacío");
	    }
	    if (dto.getDescripcion() == null || dto.getDescripcion().isBlank()) {
	        throw new IllegalArgumentException("La descripción no puede estar nula o vacía");
	    }
	    MotivoCancelacion motivoCancelacion = new MotivoCancelacion();
	    motivoCancelacion.setNombre(dto.getNombre());
	    motivoCancelacion.setDescripcion(dto.getDescripcion());
	    return motivoCancelacion;
	}

}