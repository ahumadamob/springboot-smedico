package com.imb2025.smedico.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.repository.HorarioAtencionRepository;
import com.imb2025.smedico.service.IHorarioAtencionService;

@Service
public class HorarioAtencionServiceImp implements IHorarioAtencionService {

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
	public HorarioAtencion save(HorarioAtencion horarioAtencionEntity) {
		return repo.save(horarioAtencionEntity);
	}

	@Override
	public void deleteHorarioAtencion(Long id) {
		repo.deleteById(id);
	}

}