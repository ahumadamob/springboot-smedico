package com.imb2025.smedico.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.repository.HorarioAtencionRepository;
import com.imb2025.smedico.service.HorarioAtencionService;

@Service
public class IHorarioAtencionServiceImp implements HorarioAtencionService {

	@Autowired
	private HorarioAtencionRepository repo;

	@Override
	public List<HorarioAtencion> getAllHorarioAtencion() {
		return repo.findAll();
	}

	@Override
	public HorarioAtencion getHorarioAtencionById(Long id) {
		Optional<HorarioAtencion> opt;
		opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public HorarioAtencion createHorarioAtencion(HorarioAtencion horarioAtencionEntity) {
		return repo.save(horarioAtencionEntity);
	}

	@Override
	public HorarioAtencion updateHorarioAtencion(Long id, HorarioAtencion horarioAtencionEntity) {
		if (repo.existsById(id)) {
			horarioAtencionEntity.setId(id);
			return repo.save(horarioAtencionEntity);
		}
		throw new IllegalArgumentException("Afiliación no encontrada");
	}

	@Override
	public void deleteHorarioAtencion(Long id) {
		repo.deleteById(id);
	}

}