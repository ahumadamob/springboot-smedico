package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EstudioRequestDTO;
import com.imb2025.smedico.entity.Estudio;



public interface IEstudioService {
	public List<Estudio> findAll();
	public Estudio findById(Long id);
	public Estudio create(EstudioRequestDTO dto) throws Exception;
	public void deleteById(Long id);
	public Estudio update(EstudioRequestDTO dto, Long id) throws Exception;
	
}
