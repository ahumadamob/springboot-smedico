
package com.imb2025.smedico.service.jpa;

import java.util.List; 

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.service.IOrdenEstudioService;

@Service
public class OrdenEstudioServiceImpl implements IOrdenEstudioService{

	@Autowired
	private OrdenEstudioRepository repo;

	@Override
	public List<OrdenEstudio> findAll() {
		return repo.findAll();
	}

	@Override
	public OrdenEstudio findById(Long id) {
          Optional<OrdenEstudio> ord = repo.findById(id);
          return ord.orElse(null);
	}

	@Override
	public OrdenEstudio save(OrdenEstudio ordenestudio) {
		return repo.save(ordenestudio);
		
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}

	
	
	
}
