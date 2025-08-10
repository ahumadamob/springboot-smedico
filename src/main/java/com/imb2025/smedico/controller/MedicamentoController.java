package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.MedicamentoRequestDTO;
import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.service.IMedicamentoService;

@RestController
public class MedicamentoController {
	
	@Autowired
	private IMedicamentoService service;
	
	@GetMapping("/medicamento")
	public ResponseEntity<List<Medicamento>> findAllMedicamentos(){
		List<Medicamento> listaMedic = service.findAll();
		if(listaMedic.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(listaMedic);
	}
	
	@GetMapping("/medicamento/{idmedicamento}")
	public ResponseEntity<Medicamento> findMedicamentoById(@PathVariable("idmedicamento") Long id) {
		Medicamento medicamento = service.findById(id);
		if(medicamento == null) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(medicamento);
	}
	
	@PostMapping("/medicamento")
	public ResponseEntity<Medicamento> createMedicamento(@RequestBody MedicamentoRequestDTO dto) throws Exception {
		Medicamento medicamento = service.fromDto(dto);
		Medicamento createMedic = service.create(medicamento);
		return ResponseEntity.ok(createMedic);
	}
	
	@PutMapping("/medicamento/{id}")
	public ResponseEntity<Medicamento> updateMedicamento(@PathVariable Long id, @RequestBody MedicamentoRequestDTO dto) throws Exception {
		Medicamento medicamento = service.fromDto(dto);
		Medicamento updateMedic = service.update(id, medicamento);
		return ResponseEntity.ok(updateMedic);
	}
	
	@DeleteMapping("/medicamento/{idmedicamento}")
	public ResponseEntity<String> deleteMedicamento(@PathVariable("idmedicamento") Long id) {
		service.deleteById(id);
		return ResponseEntity.ok("Medicamento " + id + " eliminado correctamente. ");
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
	  return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
	
	
}