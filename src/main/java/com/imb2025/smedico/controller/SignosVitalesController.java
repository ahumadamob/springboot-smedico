package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.mapper.RecetaMapper;
import com.imb2025.smedico.mapper.SignosVitalesMapper;
import com.imb2025.smedico.dto.request.SignosVitalesRequestDto;
import com.imb2025.smedico.dto.response.RecetaResponseDto;
import com.imb2025.smedico.dto.response.SignosVitalesResponseDto;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.entity.SignosVitales;
import com.imb2025.smedico.service.ISignosVitalesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/signosVitales")
public class SignosVitalesController {

	@Autowired
	private ISignosVitalesService service;
	
	@Autowired
	private SignosVitalesMapper mapper;

	@GetMapping
	public ResponseEntity<?> getAllSignosVitales() {
		List<SignosVitales> lista = service.findAll();
		if (lista.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<SignosVitalesResponseDto> dtos = lista.stream().map(mapper::toDto).collect(Collectors.toList());
		ApiResponseSuccessDto<List<SignosVitalesResponseDto>> resp = new ApiResponseSuccessDto<>(true,
				"Lista de signos vitales", dtos);
		return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/destacados")
    public ResponseEntity<ApiResponseSuccessDto<List<SignosVitalesResponseDto>>> findAllDestacados() {
        List<SignosVitales> lista = service.findByDestacado(true);
        List <SignosVitalesResponseDto> listaReponse = new  ArrayList<SignosVitalesResponseDto>();
        SignosVitalesMapper mapper = new SignosVitalesMapper();
        for(SignosVitales m: lista) {
        	SignosVitalesResponseDto dto = new SignosVitalesResponseDto();
        	dto = mapper.toDto(m);
        	listaReponse.add(dto);
        }
        
        ApiResponseSuccessDto<List<SignosVitalesResponseDto>> resp;

        if (lista.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true,"No hay signos vitales disponibles",listaReponse);
        } else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de signos vitales",listaReponse);
        }
        return ResponseEntity.ok(resp);       
    }	
	
	
	@GetMapping("/nodestacados")
    public ResponseEntity<ApiResponseSuccessDto<List<SignosVitalesResponseDto>>> findAllNoDestacados() {
        List<SignosVitales> lista = service.findByDestacado(false);
        List <SignosVitalesResponseDto> listaReponse = new  ArrayList<SignosVitalesResponseDto>();
        SignosVitalesMapper mapper = new SignosVitalesMapper();
        for(SignosVitales m: lista) {
        	SignosVitalesResponseDto dto = new SignosVitalesResponseDto();
        	dto = mapper.toDto(m);
        	listaReponse.add(dto);
        }
        
        ApiResponseSuccessDto<List<SignosVitalesResponseDto>> resp;

        if (lista.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true,"No hay signos vitales disponibles",listaReponse);
        } else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de signos vitales",listaReponse);
        }
        return ResponseEntity.ok(resp);       
    }	

	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<SignosVitalesResponseDto>> getSignosVitalesById(@PathVariable Long id) {
		SignosVitales data = service.findById(id);
		SignosVitalesResponseDto dto = mapper.toDto(data);
		ApiResponseSuccessDto<SignosVitalesResponseDto> resp = new ApiResponseSuccessDto<>(true,
				"Signos Vitales encontrados", dto);
		return ResponseEntity.ok(resp);
	}

	@PostMapping
	public ResponseEntity<ApiResponseSuccessDto<SignosVitalesResponseDto>> createSignosVitales(
			@Valid @RequestBody SignosVitalesRequestDto dto) {
		SignosVitales creada = service.create(mapper.fromDto(dto));
		SignosVitalesResponseDto respDto = mapper.toDto(creada);

		ApiResponseSuccessDto<SignosVitalesResponseDto> resp = new ApiResponseSuccessDto<>(true,
				"Signos Vitales creados con éxito", respDto);

		return ResponseEntity.status(201).body(resp);
	}

	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<SignosVitalesResponseDto>> updateSignosVitales(@PathVariable Long id,
			@Valid @RequestBody SignosVitalesRequestDto dto) {
		SignosVitales actualizada = service.update(id, mapper.fromDto(dto));
		SignosVitalesResponseDto respDto = mapper.toDto(actualizada);

		ApiResponseSuccessDto<SignosVitalesResponseDto> resp = new ApiResponseSuccessDto<>(true,
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
