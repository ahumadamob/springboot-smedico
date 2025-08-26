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
import com.imb2025.smedico.dto.ResultadoEstudioRequestDto;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.service.IResultadoEstudioService;

@RestController
public class ResultadoEstudioController {

	@Autowired
	private IResultadoEstudioService service;
	
	@GetMapping("/ResultadoEstudio")
	public ResponseEntity<ApiResponseSuccessDto<List<ResultadoEstudio>>>findAll(){
		List<ResultadoEstudio> resultadoEstudio = service.findAll();
		ApiResponseSuccessDto<List<ResultadoEstudio>> resp;
		
		if(resultadoEstudio.isEmpty()) {
			
			resp = new ApiResponseSuccessDto<>(true,"No hay Estudios disponibles",resultadoEstudio);
        }else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de Estudios",resultadoEstudio);
	}
		return ResponseEntity.ok(resp);
	}
	
	
	@GetMapping("/ResultadoEstudio/{idResultadoEstudio}")
	public ResponseEntity<ApiResponseSuccessDto<ResultadoEstudio>> findById(@PathVariable("idResultadoEstudio") long id) {

		ResultadoEstudio resultadoEstudio = service.findById(id);
		ApiResponseSuccessDto<ResultadoEstudio> resp =
				new ApiResponseSuccessDto<>(true,"Estudio encontrado",resultadoEstudio);
		
		return ResponseEntity.ok(resp);
	}
	
	
	@PostMapping("/ResultadoEstudio")
	public ResponseEntity<ApiResponseSuccessDto<ResultadoEstudio>> createResultadoEstudio(@RequestBody ResultadoEstudioRequestDto requestDto) throws Exception {
		
		ResultadoEstudio resultadoEstudio = service.create(service.fromDto(requestDto));
		ApiResponseSuccessDto<ResultadoEstudio> resp =
				new ApiResponseSuccessDto<>(true,"Estudio creado correctamente",resultadoEstudio);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		
	}
	
	
	@PutMapping ("/ResultadoEstudio/{id}")
	public ResponseEntity<ApiResponseSuccessDto<ResultadoEstudio>>  updateResultadoEstudio(@PathVariable Long id,
			@RequestBody ResultadoEstudioRequestDto requestDto) throws Exception {
		ResultadoEstudio resultadoEstudioEntity = service.fromDto(requestDto);
		ResultadoEstudio actualizado = service.update(id, resultadoEstudioEntity);
		ApiResponseSuccessDto<ResultadoEstudio> resp =
				new ApiResponseSuccessDto<>(true,"Estudio actualizado correctamente",actualizado);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping ("/ResultadoEstudio/{idResultadoEstudio}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteResultadoEstudio (@PathVariable("idResultadoEstudio") Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp =
				new ApiResponseSuccessDto<>(true,"Estudio eliminado correctamente", "Id: " + id);
		
		return ResponseEntity.ok(resp);	
		}
	
}
