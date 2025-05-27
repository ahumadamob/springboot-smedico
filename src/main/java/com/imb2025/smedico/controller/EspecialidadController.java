package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.service.IEspecialidadService;

@RestController
public class EspecialidadController {
	
	@Autowired
	private IEspecialidadService service;
	
	
	@GetMapping("/especialidad")
	public List<Especialidad> findAllEspecialidad(){
		return service.findall();
	}
	
	@GetMapping("/especialidad/{idespecialidad}")
	public Especialidad findEspecialidadById(@PathVariable("idespecialidad") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/especialidad")
	public Especialidad createEspecialidad(@RequestBody Especialidad especialidad) {
		return service.save(especialidad);
	}
	 
	@PutMapping("/especialidad")
	public Especialidad updateEspecialidad(@RequestBody Especialidad especialidad) {
		return service.save(especialidad);
	}
	
	@DeleteMapping("/especialidad/{idespecialidad}")
	public String deleteEspecialidad(@PathVariable("idespecialidad") Long id) {
		service.deleteById(id);
		return "Especialidad " +id.toString() + "eliminada correctamente. ";
	}
}
