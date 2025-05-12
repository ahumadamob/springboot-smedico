package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.service.IMedicamentoService;

@RestController
public class MedicamentoController {
	
	@Autowired
	private IMedicamentoService service;
	
	@GetMapping("/medicamento")
	public List<Medicamento> findAllMedicamentos(){
		return service.findAll();
	}
	
	@GetMapping("/medicamento/{idmedicamento}")
	public Medicamento findMedicamentoById(@PathVariable("idmedicamento") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/medicamento")
	public Medicamento createMedicamento(@RequestBody Medicamento medicamento) {
		return service.save(medicamento);
	}
	
	@PutMapping("/medicamento")
	public Medicamento updateMedicamento(@RequestBody Medicamento medicamento) {
		return service.save(medicamento);
	}
	
	@DeleteMapping("/medicamento/{idmedicamento}")
	public String deleteMedicamento(@PathVariable("idmedicamento") Long id) {
		service.deleteById(id);
		return "Medicamento " + id.toString() + " eliminado correctamente. ";
	}

}