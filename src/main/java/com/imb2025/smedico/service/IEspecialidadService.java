package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Especialidad;

import com.imb2025.smedico.dto.EspecialidadRequestDto;

public interface IEspecialidadService {

	public List<Especialidad> findall();
	public Especialidad findById(Long id);
	public Especialidad create(Especialidad especialidad);
	public Especialidad update(Long id, Especialidad especialidad) throws Exception;
	public void deleteById(Long id);
	public Especialidad fromDto(EspecialidadRequestDto dto) throws Exception;
	
	
	
}
