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
import com.imb2025.smedico.dto.ApiResponseSuccessDto;

import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.service.IEspecialidadService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.EspecialidadRequestDto;

@RestController
public class EspecialidadController {
	
	@Autowired
	private IEspecialidadService service;
	
	
        @GetMapping("/especialidad")
        public ResponseEntity<ApiResponseSuccessDto<List<Especialidad>>> findAllEspecialidad(){
                List<Especialidad> especialidad = service.findAll();
                ApiResponseSuccessDto<List<Especialidad>> resp;
                if (especialidad.isEmpty()) {
                	resp = new ApiResponseSuccessDto<>(true,"No hay especialidades disponibles",especialidad);
                }else {
                	resp = new ApiResponseSuccessDto<>(true,"Lista de especialidades",especialidad);
        }
                return ResponseEntity.ok(resp);
        }
        
        @GetMapping("/especialidad/{idespecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<Especialidad>> findEspecialidadById(@PathVariable("idespecialidad") Long id) {
                Especialidad especialidad = service.findById(id);
                ApiResponseSuccessDto<Especialidad> resp = new ApiResponseSuccessDto<>(true, "Especialidad encontrada", especialidad);
                return ResponseEntity.ok(resp);
        }
	
	@PostMapping("/especialidad")
	public ResponseEntity<ApiResponseSuccessDto<Especialidad>> create(@Valid @RequestBody EspecialidadRequestDto dto) {
		Especialidad especialidad = service.create(service.fromDto(dto));
		ApiResponseSuccessDto<Especialidad> resp = new ApiResponseSuccessDto<>(true, "Especialidad creada correctamente",especialidad);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
	}
	 
	@PutMapping("/especialidad/{idespecialidad}")
	public ResponseEntity<ApiResponseSuccessDto<Especialidad>> update(@Valid @RequestBody EspecialidadRequestDto dto, @PathVariable("idespecialidad") Long id){
			 Especialidad especialidadEntity = service.fromDto(dto);
			 Especialidad actualizado = service.update(id, especialidadEntity);
			 ApiResponseSuccessDto<Especialidad> resp = new ApiResponseSuccessDto<>(true, "Especialidad actualizada correctamente",actualizado);
			 return ResponseEntity.ok(resp);
			 
	}
	
	@DeleteMapping("/especialidad/{idespecialidad}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteEspecialidad(@PathVariable("idespecialidad") Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true, "Especialidad eliminada correctamente","Id: " +id);
		return ResponseEntity.ok(resp);
		
	}
	
}
