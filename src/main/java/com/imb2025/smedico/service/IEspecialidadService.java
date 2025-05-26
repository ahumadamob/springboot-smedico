package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Especialidad;

public interface IEspecialidadService {

	public List<Especialidad> findall();
	public Especialidad findById(Long id);
	public Especialidad save(Especialidad especialidad);
	public void deleteById(Long id);
	
}
