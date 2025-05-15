package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Medicamento;

public interface IMedicamentoService {
	
	public List<Medicamento> findAll();
	public Medicamento findById(Long id);
	public Medicamento save(Medicamento medicamento);
	public void deleteById(Long id);
	
}