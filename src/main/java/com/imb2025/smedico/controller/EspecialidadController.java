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

import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.service.IEspecialidadService;

import com.imb2025.smedico.dto.EspecialidadRequestDto;

@RestController
public class EspecialidadController {
	
	@Autowired
	private IEspecialidadService service;
	
	
	@GetMapping("/especialidad")
	public List<Especialidad> findAllEspecialidad(){
		return service.findall();
	}
	
	@GetMapping("/especialidad/{idespecialidad}")
	public ResponseEntity<Especialidad> findEspecialidadById(@PathVariable("idespecialidad") Long id) {	 
		Especialidad especialidad = new Especialidad();
		especialidad = service.findById(id);
		 if(especialidad == null) {
			 return ResponseEntity.badRequest().body(null);	 
		 }else {
			 return ResponseEntity.ok(especialidad);
		 }
	}
	
	@PostMapping("/especialidad")
	public ResponseEntity<Especialidad> create(@RequestBody EspecialidadRequestDto dto) throws Exception {
		return ResponseEntity.ok(service.create(service.fromDto(dto)));
		
	}
	 
	@PutMapping("/especialidad/{idespecialidad}")
	public ResponseEntity<Especialidad> update(@RequestBody EspecialidadRequestDto dto, @PathVariable("idespecialidad") Long id) throws Exception {
			 Especialidad entity = service.fromDto(dto);
			 return ResponseEntity.ok(service.update(id, entity));
	}
	
	@DeleteMapping("/especialidad/{idespecialidad}")
	public ResponseEntity<String> deleteEspecialidad(@PathVariable("idespecialidad") Long id) {
		service.deleteById(id);
		return ResponseEntity.ok("Especialidad " + id + " eliminada correctamente.");
	}
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalException(Exception ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
