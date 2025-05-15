package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.repository.RecetaRepository;
import com.imb2025.smedico.service.IRecetaService;
@Service
public class RecetaImpl implements IRecetaService{
	
	@Autowired
	private RecetaRepository repo;

	@Override
	public List<Receta> findAll() {
		return repo.findAll();
	}

	@Override
	public Receta findById(Long id) {
		Optional<Receta> opt;
        opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
	}

	@Override
	public Receta save(Receta receta) {
		return repo.save(receta);
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
	

}
