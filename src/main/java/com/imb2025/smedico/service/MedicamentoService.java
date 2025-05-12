package com.imb2025.smedico.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.repository.MedicamentoRepository;

@Service
public class MedicamentoService {
	
	@Autowired
	private MedicamentoRepository repoMedic;
	
	public List<Medicamento> obtenerMedicamentos(){
		
		List<Medicamento> medicamentos = new ArrayList<Medicamento>();
		
		return medicamentos;
	}
	
	public Medicamento obtenerUnMedicamento() {
		
		Medicamento medicamento = new Medicamento();
				
		return medicamento;	
	}
	
	public List<Medicamento> obtenerTodosMedicamentosDesdeLaBase() {
		
		return repoMedic.findAll();
	}
}