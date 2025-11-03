package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

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
import com.imb2025.smedico.dto.request.MedicamentoRequestDto;
import com.imb2025.smedico.dto.response.MedicamentoResponseDto;
import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.mapper.MedicamentoMapper;
import com.imb2025.smedico.service.IMedicamentoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/medicamento")
public class MedicamentoController {
	
	@Autowired
	private IMedicamentoService service;
	
    private final MedicamentoMapper mapper = new MedicamentoMapper();
	
	@GetMapping
	public ResponseEntity<ApiResponseSuccessDto<List<MedicamentoResponseDto>>> findAllMedicamentos(){
		List<Medicamento> listaMedic = service.findAll();
		
		List<MedicamentoResponseDto> listaDto = listaMedic.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
		
		String message = listaMedic.isEmpty() ? "No hay medicamentos disponibles" : "Lista de medicamentos";
		ApiResponseSuccessDto<List<MedicamentoResponseDto>> resp = new ApiResponseSuccessDto<>(true, message, listaDto);
		
		return ResponseEntity.ok(resp);
		
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<MedicamentoResponseDto>> findMedicamentoById(@PathVariable Long id) {
		Medicamento medicamento = service.findById(id);
		MedicamentoResponseDto dto = mapper.toDto(medicamento);
		ApiResponseSuccessDto<MedicamentoResponseDto> resp = new ApiResponseSuccessDto<>(true, "Medicamento encontrado", dto);
		return ResponseEntity.ok(resp);
	}
		
	@PostMapping
	public ResponseEntity<ApiResponseSuccessDto<MedicamentoResponseDto>> createMedicamento(@Valid @RequestBody MedicamentoRequestDto dto) {
		Medicamento medicamento = mapper.fromDto(dto);
	    Medicamento guardado = service.create(medicamento);
	    MedicamentoResponseDto responseDto = mapper.toDto(guardado);
	    ApiResponseSuccessDto<MedicamentoResponseDto> resp = new ApiResponseSuccessDto<>(true, "Medicamento agregado correctamente", responseDto);
	    return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<MedicamentoResponseDto>> updateMedicamento(@PathVariable Long id, @Valid @RequestBody MedicamentoRequestDto dto) {
		Medicamento medicamento = mapper.fromDto(dto);
		Medicamento actualizado = service.update(id, medicamento);
		MedicamentoResponseDto responseDto = mapper.toDto(actualizado);
		ApiResponseSuccessDto<MedicamentoResponseDto> resp = new ApiResponseSuccessDto<>(true, "Medicamento actualizado correctamente", responseDto);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteMedicamento(@PathVariable Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true, "Medicamento eliminado correctamente", "ID: "+id);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ApiResponseSuccessDto<List<MedicamentoResponseDto>>> findByNombre(@PathVariable String nombre) {
	    List<Medicamento> lista = service.findByNombre(nombre);
	    List<MedicamentoResponseDto> listaDto = lista.stream()
                .map(mapper::toDto)
                .collect(Collectors.toList());
	    String message = lista.isEmpty() ? "No se encontraron medicamentos con ese nombre" : "Medicamentos encontrados";
	    ApiResponseSuccessDto<List<MedicamentoResponseDto>> resp = new ApiResponseSuccessDto<>(true, message, listaDto);
	    return ResponseEntity.ok(resp);
	}

	@GetMapping("/count/{presentacion}")
	public ResponseEntity<ApiResponseSuccessDto<Long>> countByPresentacion(@PathVariable String presentacion) {
	    Long cantidad = service.countByPresentacion(presentacion);
	    String message = "Cantidad de medicamentos con presentación '" + presentacion + "': " + cantidad;
	    ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true, message, cantidad);
	    return ResponseEntity.ok(resp);
	}
	
	
	
}