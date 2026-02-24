package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;	
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.service.IRecetaService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.RecetaRequestDto;
import com.imb2025.smedico.dto.response.RecetaResponseDto;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.mapper.RecetaMapper;

@RestController
public class RecetaController {
	
	@Autowired
	private IRecetaService service;
	@Autowired
	private RecetaMapper mapper;
	
	@GetMapping("/receta")
    public ResponseEntity<ApiResponseSuccessDto<List<RecetaResponseDto>>> findAllReceta() {
        List<Receta> lista = service.findAll();
        List <RecetaResponseDto> listaReponse = new  ArrayList<RecetaResponseDto>();
		RecetaMapper mapper = new RecetaMapper();
        for(Receta m: lista) {
        	RecetaResponseDto dto = new RecetaResponseDto();
        	dto = mapper.toDto(m);
        	listaReponse.add(dto);
        }
        
        ApiResponseSuccessDto<List<RecetaResponseDto>> resp;

        if (lista.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true,"No hay recetas disponibles",listaReponse);
        } else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de recetas",listaReponse);
        }
        return ResponseEntity.ok(resp);       
    }
	
	@GetMapping("/receta/{id}")
	public ResponseEntity<ApiResponseSuccessDto<RecetaResponseDto>> findById(@PathVariable("id") Long id) {
		Receta receta = service.findById(id);
		RecetaResponseDto dto = new RecetaResponseDto();
		dto = mapper.toDto(receta);
		ApiResponseSuccessDto<RecetaResponseDto> resp =
				new ApiResponseSuccessDto<>(true,"Receta encontrada", dto);
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/receta")
	public ResponseEntity<ApiResponseSuccessDto<Receta>> create(@Valid @RequestBody RecetaRequestDto recetaRequestDto) {
        Receta receta = service.create(mapper.fromDto(recetaRequestDto));
        ApiResponseSuccessDto<Receta> resp =
				new ApiResponseSuccessDto<>(true,"Receta creada correctamente",receta);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
	
	@PutMapping("/receta/{id}")
	public ResponseEntity<ApiResponseSuccessDto<Receta>> update(@PathVariable("id") Long id,@Valid @RequestBody RecetaRequestDto recetaRequestDto) {
		Receta recetaEntity = mapper.fromDto(recetaRequestDto);
        Receta actualizado = service.update(id, recetaEntity);
		ApiResponseSuccessDto<Receta> resp =
				new ApiResponseSuccessDto<>(true,"Receta actualizada",actualizado);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping("/receta/{id}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteReceta(@PathVariable("id") Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp = 
        		new ApiResponseSuccessDto<>(true, "Receta eliminada correctamente", "id: "+id);
        return ResponseEntity.ok(resp);
    }
	@GetMapping("/find/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<List<Receta>>> getRecetasPorFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
        List<Receta> lista = service.findByFecha(fecha);
        ApiResponseSuccessDto<List<Receta>> resp = new ApiResponseSuccessDto<>(
            true,
            lista.isEmpty() ? "No hay turnos para la fecha indicada" : "Turnos por fecha",
            lista
        );
        return ResponseEntity.ok(resp);
    }
	@GetMapping("/count/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countRecetasPorFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {
	    long cantidad = service.countByFecha(fecha);
        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true, cantidad == 0 ? "No hay recetas para la fecha indicada" : "Cantidad de recetas encontradas", cantidad);
        return ResponseEntity.ok(resp);
    
	}
	

	
}


