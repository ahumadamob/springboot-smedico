package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ResultadoEstudioRequestDTO;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.service.IResultadoEstudioService;

@RestController
public class ResultadoEstudioController {

	@Autowired
	private IResultadoEstudioService service;
	
	@GetMapping("/ResultadoEstudio")
	public ResponseEntity<List<ResultadoEstudio>>findAll(){
		List<ResultadoEstudio> resultadoEstudio = service.findAll();
		
		if(resultadoEstudio.isEmpty()) {
			
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(resultadoEstudio);
	}
	
	
	@GetMapping("/ResultadoEstudio/{idResultadoEstudio}")
	public ResponseEntity<ResultadoEstudio> findById(@PathVariable("idResultadoEstudio") long id) {

		ResultadoEstudio resultadoEstudio = service.findById(id);
		if(resultadoEstudio == null) {
			return ResponseEntity.notFound().build();
			
		}
		return ResponseEntity.ok(resultadoEstudio);
	}
	
	@PostMapping("/ResultadoEstudio")
	public ResponseEntity<ResultadoEstudio> createResultadoEstudio(@RequestBody ResultadoEstudioRequestDTO requestDto) throws Exception {
		ResultadoEstudio resultadoEstudio = service.create(service.fromDto(requestDto));
		return ResponseEntity.ok(resultadoEstudio);
		
	}
	
	
	@PutMapping ("/ResultadoEstudio/{id}")
	public ResponseEntity<ResultadoEstudio>  updateResultadoEstudio(@PathVariable Long id,@RequestBody ResultadoEstudioRequestDTO requestDto) throws Exception {
		return ResponseEntity.ok(service.update(id, service.fromDto(requestDto)));
	}
	
	@DeleteMapping ("/ResultadoEstudio/{idResultadoEstudio}")
	public ResponseEntity<String> deleteResultadoEstudio (@PathVariable("idResultadoEstudio") Long id) {
		service.deleteById(id);
		return ResponseEntity.ok("Estudio "+id.toString()+ " Eliminado Correctamente");	
		}
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
	  return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
}
