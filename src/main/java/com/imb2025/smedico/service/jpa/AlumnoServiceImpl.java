package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Alumno;
import com.imb2025.smedico.repository.AlumnoRepository;
import com.imb2025.smedico.service.IAlumnoService;
@Service
public class AlumnoServiceImpl implements IAlumnoService {
	
	@Autowired
	private AlumnoRepository repo;

	@Override
	public List<Alumno> findAll() {
		return repo.findAll();
	}

	public Alumno findById(Long id) {
		Optional<Alumno> opt;
		opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}

	@Override
	public Alumno save(Alumno alumno) {
		return repo.save(alumno);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);		
	}

}
