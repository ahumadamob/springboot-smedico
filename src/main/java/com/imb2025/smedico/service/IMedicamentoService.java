package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.MedicamentoRequestDTO;
import com.imb2025.smedico.entity.Medicamento;

public interface IMedicamentoService {
	
	public List<Medicamento> findAll();
	public Medicamento findById(Long id);
	public Medicamento create(MedicamentoRequestDTO dto) throws Exception;
	public Medicamento update(Long id, MedicamentoRequestDTO dto) throws Exception;
	public void deleteById(Long id);
	public Medicamento fromDto(MedicamentoRequestDTO dto) throws Exception;
	
}