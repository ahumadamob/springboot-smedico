package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Alumno;

public interface IAlumnoService {
	
	public List<Alumno> findAll();
	public Alumno findById(Long id);
	public Alumno save(Alumno alumno);
	public void deleteById(Long id);

}
