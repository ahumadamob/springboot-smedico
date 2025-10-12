package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.SignosVitalesRequestDto;
import com.imb2025.smedico.entity.SignosVitales;
import com.imb2025.smedico.service.ISignosVitalesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/signosVitales")
public class SignosVitalesController {

	@Autowired
	private ISignosVitalesService service;

	@GetMapping
	public ResponseEntity<?> getAllSignosVitales() {
		List<SignosVitales> lista = service.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<SignosVitalesRequestDto> dtos = lista.stream().map(service::toDto).collect(Collectors.toList());
		ApiResponseSuccessDto<List<SignosVitalesRequestDto>> resp = new ApiResponseSuccessDto<>(true,
				"Lista de signos vitales", dtos);
		return ResponseEntity.ok(resp);
	}

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<SignosVitalesRequestDto>> getSignosVitalesById(@PathVariable Long id) {
		SignosVitales data = service.findById(id);
		SignosVitalesRequestDto dto = service.toDto(data);
		ApiResponseSuccessDto<SignosVitalesRequestDto> resp = new ApiResponseSuccessDto<>(true,
				"Signos Vitales encontrados", dto);
		return ResponseEntity.ok(resp);
	}

	@PostMapping
	public ResponseEntity<ApiResponseSuccessDto<SignosVitalesRequestDto>> createSignosVitales(
			@Valid @RequestBody SignosVitalesRequestDto dto) {

		SignosVitales entity = service.fromDto(dto);
		SignosVitales creada = service.create(entity);
		SignosVitalesRequestDto respDto = service.toDto(creada);

		ApiResponseSuccessDto<SignosVitalesRequestDto> resp = new ApiResponseSuccessDto<>(true,
				"Signos Vitales creados con éxito", respDto);

		return ResponseEntity.status(201).body(resp);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<SignosVitalesRequestDto>> updateSignosVitales(@PathVariable Long id,
			@Valid @RequestBody SignosVitalesRequestDto dto) {

		SignosVitales entity = service.fromDto(dto);
		SignosVitales actualizada = service.update(id, entity);
		SignosVitalesRequestDto respDto = service.toDto(actualizada);

		ApiResponseSuccessDto<SignosVitalesRequestDto> resp = new ApiResponseSuccessDto<>(true,
				"Signos Vitales actualizados con éxito", respDto);

		return ResponseEntity.ok(resp);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteSignosVitales(@PathVariable Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true, "Signos Vitales eliminados con éxito",
				null);
		return ResponseEntity.ok(resp);
	}

	// /signosVitales/fechas?inicio=2025-10-01&fin=2025-10-05
	@GetMapping("/fechas")
    public List<SignosVitales> getByFechas(
            @RequestParam("inicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam("fin") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fin) {

        return service.findByFechas(inicio, fin);
    }
	
	
	@GetMapping("/count/{idConsulta}")
    public Long countByConsulta(@PathVariable Long idConsulta) {
        return service.countByConsulta(idConsulta);
    }

}
