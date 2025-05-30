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
import com.imb2025.smedico.entity.EstudioEntity;
import com.imb2025.smedico.service.jpa.IEstudioService;

@RestController
public class EstudioController {
	@Autowired
	private IEstudioService service;
	
	@GetMapping("/Estudio")
	public List<EstudioEntity>findAllEstudio(){
		return service.findAll();
	}
	
	@GetMapping("/Estudio/{idestudio}")
	public EstudioEntity findEstudioByid(@PathVariable("idestudio") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/Estudio")
	public EstudioEntity Estudio(@RequestBody EstudioEntity estudio) {
		return service.save(estudio);
	}
	
	@PutMapping("/Estudio")
	public EstudioEntity updateEstudio(@RequestBody EstudioEntity estudio) {
		return service.save(estudio);
	}
	
	@DeleteMapping("/Estudio/{idestudio}")
	public String deleteEstudio(@PathVariable("idestudio") Long id) {
		service.deleteById(id);
		return "Estudio"+id.toString()+ " Eliminado Correctamente";
	}
}
