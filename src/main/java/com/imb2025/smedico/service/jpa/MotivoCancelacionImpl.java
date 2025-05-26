package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public MotivoCancelacion save(MotivoCancelacion motivocancelacion) {
		return repo.save(motivocancelacion);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
}
