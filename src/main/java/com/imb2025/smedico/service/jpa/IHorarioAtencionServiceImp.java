package com.imb2025.smedico.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.imb2025.smedico.entity.HorarioAtencionEntity;
import com.imb2025.smedico.repository.HorarioAtencionRepository;
import com.imb2025.smedico.service.HorarioAtencionService;

@Service
public class IHorarioAtencionServiceImp implements HorarioAtencionService {

	@Autowired
	private HorarioAtencionRepository repo;

	@Override
	public List<HorarioAtencionEntity> getAllHorarioAtencion() {
		return repo.findAll();
	}

	@Override
	public HorarioAtencionEntity getHorarioAtencionById(Long id) {
		Optional<HorarioAtencionEntity> opt;
		opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public HorarioAtencionEntity createHorarioAtencion(HorarioAtencionEntity horarioAtencionEntity) {
		return repo.save(horarioAtencionEntity);
	}

	@Override
	public HorarioAtencionEntity updateHorarioAtencion(Long id, HorarioAtencionEntity horarioAtencionEntity) {
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