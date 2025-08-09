package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EstudioRequestDTO;
import com.imb2025.smedico.entity.Estudio;



public interface IEstudioService {
	public List<Estudio> findAll();
	public Estudio findById(Long id);
	public boolean existsById(Long id);
	public Estudio create(Estudio estudio) throws Exception;
	public Estudio update(Estudio estudio, Long id) throws Exception;
	public void deleteById(Long id);
	public Estudio fromDto(EstudioRequestDTO dto) throws Exception;
	
}
