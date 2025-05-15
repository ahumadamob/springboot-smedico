package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.HistorialPaciente;



public interface IHistorialPacienteService {
	
	public List<HistorialPaciente> findAll();
	public HistorialPaciente findById(Long id);
	public HistorialPaciente save(HistorialPaciente historial);
	public void deleteById(Long id);

}
