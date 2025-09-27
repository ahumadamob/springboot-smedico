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
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.service.IRecetaService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.RecetaRequestDto;
import com.imb2025.smedico.entity.Receta;

@RestController
public class RecetaController {
	
	@Autowired
	private IRecetaService service;
	
	@GetMapping("/receta")
    public ResponseEntity<ApiResponseSuccessDto<List<Receta>>> findAllReceta() {
        List<Receta> lista = service.findAll();
        ApiResponseSuccessDto<List<Receta>> resp;
        if (lista.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true,"No hay recetas disponibles",lista);
        } else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de recetas",lista);
        }
        return ResponseEntity.ok(resp);       
    }
	
	@GetMapping("/receta/{id}")
	public ResponseEntity<ApiResponseSuccessDto<Receta>> findById(@PathVariable("id") Long id) {
		Receta receta = service.findById(id);
		ApiResponseSuccessDto<Receta> resp =
				new ApiResponseSuccessDto<>(true,"Receta encontrada",receta);
		return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/receta")
	public ResponseEntity<ApiResponseSuccessDto<Receta>> create(@Valid @RequestBody RecetaRequestDto recetaRequestDto) {
        Receta receta = service.create(service.fromDto(recetaRequestDto));
        ApiResponseSuccessDto<Receta> resp =
				new ApiResponseSuccessDto<>(true,"Receta creada correctamente",receta);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
	
	@PutMapping("/receta/{id}")
	public ResponseEntity<ApiResponseSuccessDto<Receta>> update(@PathVariable("id") Long id,@Valid @RequestBody RecetaRequestDto recetaRequestDto) {
        Receta recetaEntity = service.fromDto(recetaRequestDto);
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
}


