package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Especialidad;

import dto.EspecialidadRequestDTO;

public interface IEspecialidadService {

	public List<Especialidad> findall();
	public Especialidad findById(Long id);
	public Especialidad create(EspecialidadRequestDTO dto);
	public Especialidad update(Long id, EspecialidadRequestDTO dto) throws Exception;
	public void deleteById(Long id);
	
	
	
}
