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
import com.imb2025.smedico.dto.MedicamentoRequestDto;
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
	
	@Autowired
    private MedicamentoMapper mapper;
	
	@GetMapping
	public ResponseEntity<ApiResponseSuccessDto<List<MedicamentoResponseDto>>> findAllMedicamentos(){
		List<Medicamento> listaMedic = service.findAll();
		
        // Convertimos la lista de Entidades a DTOs
        List<MedicamentoResponseDto> listaDto = listaMedic.stream()
                .map(mapper::toResponseDto) // Shorthand para (medicamento -> mapper.toResponseDto(medicamento))
                .collect(Collectors.toList());

		String message = listaDto.isEmpty() ? "No hay medicamentos disponibles" : "Lista de medicamentos";
		ApiResponseSuccessDto<List<MedicamentoResponseDto>> resp = new ApiResponseSuccessDto<>(true, message, listaDto);
		
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<MedicamentoResponseDto>> findMedicamentoById(@PathVariable Long id) {
		Medicamento medicamento = service.findById(id);
        MedicamentoResponseDto dto = mapper.toResponseDto(medicamento); // Usamos mapper

		ApiResponseSuccessDto<MedicamentoResponseDto> resp = new ApiResponseSuccessDto<>(true, "Medicamento encontrado", dto);
		return ResponseEntity.ok(resp);
	}
		
	@PostMapping
	public ResponseEntity<ApiResponseSuccessDto<MedicamentoResponseDto>> createMedicamento(@Valid @RequestBody MedicamentoRequestDto dto) {
		
	    Medicamento guardado = service.create(dto); // El servicio ya recibe el DTO
        MedicamentoResponseDto dtoResp = mapper.toResponseDto(guardado); // Convertimos

	    ApiResponseSuccessDto<MedicamentoResponseDto> resp = new ApiResponseSuccessDto<>(true, "Medicamento agregado correctamente", dtoResp);
	    return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<MedicamentoResponseDto>> updateMedicamento(@PathVariable Long id, @Valid @RequestBody MedicamentoRequestDto dto) {
		
		Medicamento actualizado = service.update(id, dto); // El servicio ya recibe el DTO
        MedicamentoResponseDto dtoResp = mapper.toResponseDto(actualizado); // Convertimos

		ApiResponseSuccessDto<MedicamentoResponseDto> resp = new ApiResponseSuccessDto<>(true, "Medicamento actualizado correctamente", dtoResp);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteMedicamento(@PathVariable Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true, "Medicamento eliminado correctamente", "ID: "+id);
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/activos")
    public ResponseEntity<ApiResponseSuccessDto<List<MedicamentoResponseDto>>> findActivos() {
        List<Medicamento> listaMedic = service.findActivos();
        
        List<MedicamentoResponseDto> listaDto = listaMedic.stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());

        String message = listaDto.isEmpty() ? "No hay medicamentos activos" : "Lista de medicamentos activos";
        ApiResponseSuccessDto<List<MedicamentoResponseDto>> resp = new ApiResponseSuccessDto<>(true, message, listaDto);
        
        return ResponseEntity.ok(resp);
    }
	
	@GetMapping("/inactivos")
    public ResponseEntity<ApiResponseSuccessDto<List<MedicamentoResponseDto>>> findInactivos() {
        List<Medicamento> listaMedic = service.findInactivos();
        
        List<MedicamentoResponseDto> listaDto = listaMedic.stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());

        String message = listaDto.isEmpty() ? "No hay medicamentos inactivos" : "Lista de medicamentos inactivos";
        ApiResponseSuccessDto<List<MedicamentoResponseDto>> resp = new ApiResponseSuccessDto<>(true, message, listaDto);
        
        return ResponseEntity.ok(resp);
    }

	
	
}