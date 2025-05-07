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

import com.imb2025.smedico.entity.Alumno;
import com.imb2025.smedico.service.IAlumnoService;

@RestController
public class AlumnoController {
	
	@Autowired
	private IAlumnoService service;
	
	//GET
	@GetMapping("/alumno")
	public List<Alumno> findAllAlumnos(){
		return service.findAll();
	}
	
	
	//GET (por ID)
	@GetMapping("/alumno/{idalumno}")
	public Alumno findAlumnoById(@PathVariable("idalumno") Long id) {
		return service.findById(id);
	}
	
	//POST
	@PostMapping("/alumno")
	public Alumno createAlumno(@RequestBody Alumno alumno) {
		return service.save(alumno);
		
	}
	
	//PUT
	@PutMapping("/alumno")
	public Alumno updateAlumno(@RequestBody Alumno alumno) {
		return service.save(alumno);
	}
	
	//DELETE
	@DeleteMapping("/alumno/{idalumno}")
	public String deleteAlumno(@PathVariable("idalumno") Long id) {
		service.deleteById(id);
		return "Alumno " + id.toString() + " eliminado correctamente. ";
	}
	

}
