package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.service.IMedicamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
	
	@Autowired
	private IMedicamentoService service;
	
	@GetMapping
	public ResponseEntity<ApiResponseSuccessDto<List<Medicamento>>> findAllMedicamentos(){
		List<Medicamento> listaMedic = service.findAll();
		
		String message = listaMedic.isEmpty() ? "No hay medicamentos disponibles" : "Lista de medicamentos";
		ApiResponseSuccessDto<List<Medicamento>> resp = new ApiResponseSuccessDto<>(true, message, listaMedic);
		
		return ResponseEntity.ok(resp);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<Medicamento>> findMedicamentoById(@PathVariable Long id) {
		Medicamento medicamento = service.findById(id);
		ApiResponseSuccessDto<Medicamento> resp = new ApiResponseSuccessDto<>(true, "Medicamento encontrado", medicamento);
		return ResponseEntity.ok(resp);
	}
		
	@PostMapping
	public ResponseEntity<ApiResponseSuccessDto<Medicamento>> createMedicamento(@Valid @RequestBody MedicamentoRequestDto dto) {
		Medicamento medicamento = service.fromDto(dto);
	    Medicamento guardado = service.create(medicamento);
	    ApiResponseSuccessDto<Medicamento> resp = new ApiResponseSuccessDto<>(true, "Medicamento agregado correctamente", guardado);
	    return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<Medicamento>> updateMedicamento(@PathVariable Long id, @Valid @RequestBody MedicamentoRequestDto dto) {
		Medicamento medicamento = service.fromDto(dto);
		Medicamento actualizado = service.update(id, medicamento);
		ApiResponseSuccessDto<Medicamento> resp = new ApiResponseSuccessDto<>(true, "Medicamento actualizado correctamente", actualizado);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteMedicamento(@PathVariable Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true, "Medicamento eliminado correctamente", "ID: "+id);
		return ResponseEntity.ok(resp);
	}

	
	
}