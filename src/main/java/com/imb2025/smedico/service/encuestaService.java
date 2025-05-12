package com.imb2025.smedico.service;

import java.util.List;
import java.util.Optional;

import com.imb2025.smedico.entity.encuestaEntity;


public interface encuestaService {
   
	List<encuestaEntity> findAll();
	Optional<encuestaEntity> findBy(Long id);
	encuestaEntity save(encuestaEntity encuesta);
	Optional<encuestaEntity> update(Long id, encuestaEntity encuesta );
	boolean deleteById(Long id);
	Optional<encuestaEntity> findById(Long id);
	
	
	
	
	
}
