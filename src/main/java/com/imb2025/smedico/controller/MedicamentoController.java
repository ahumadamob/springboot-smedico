package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.service.IMedicamentoService;

import jakarta.validation.Valid;

@RestController
public class MedicamentoController {
	
	@Autowired
	private IMedicamentoService service;
	
	@GetMapping("/medicamento")
	public ResponseEntity<ApiResponseSuccessDto<List<Medicamento>>> findAllMedicamentos(){
		List<Medicamento> listaMedic = service.findAll();
		ApiResponseSuccessDto<List<Medicamento>> resp;
		if(listaMedic.isEmpty()) {
			resp = new ApiResponseSuccessDto<>(true, "No hay medicamentos disponibles", listaMedic);
		}else {
			resp = new ApiResponseSuccessDto<>(true, "Lista de medicamentos", listaMedic);
		}
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/medicamento/{idmedicamento}")
	public ResponseEntity<ApiResponseSuccessDto<Medicamento>> findMedicamentoById(@PathVariable("idmedicamento") Long id) {
		Medicamento medicamento = service.findById(id);
		ApiResponseSuccessDto<Medicamento> resp = new ApiResponseSuccessDto<>(true, "Medicamento encontrado", medicamento);
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/medicamento")
	public ResponseEntity<ApiResponseSuccessDto<Medicamento>> createMedicamento(@Valid @RequestBody MedicamentoRequestDto dto) throws Exception {
		Medicamento medicamento = service.fromDto(dto);
		ApiResponseSuccessDto<Medicamento> resp = new ApiResponseSuccessDto<>(true, "Medicamento agregado correctamente", medicamento);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@PutMapping("/medicamento/{id}")
	public ResponseEntity<ApiResponseSuccessDto<Medicamento>> updateMedicamento(@PathVariable Long id, @Valid @RequestBody MedicamentoRequestDto dto) throws Exception {
		Medicamento medicamento = service.fromDto(dto);
		Medicamento actualizado = service.update(id, medicamento);
		ApiResponseSuccessDto<Medicamento> resp = new ApiResponseSuccessDto<>(true, "Medicamento actualizado correctamente", actualizado);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/medicamento/{idmedicamento}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteMedicamento(@PathVariable("idmedicamento") Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true, "Medicamento eliminado correctamente", "ID: "+id);
		return ResponseEntity.ok(resp);
	}

	
	
}