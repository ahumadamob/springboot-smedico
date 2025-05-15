package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.repository.EncuestaRepository;
import com.imb2025.smedico.service.IEncuestaService;

@Service
public class EncuestaServiceImpl implements IEncuestaService {
	
	@Autowired
	private EncuestaRepository repo;

	@Override
	public List<Encuesta> findAll() {
		return repo.findAll();
	}

	@Override
	public Encuesta findById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public Encuesta save(Encuesta encuesta) {
		return repo.save(encuesta);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

}
