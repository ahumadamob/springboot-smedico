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

import com.imb2025.smedico.service.IRecetaService;
import com.imb2025.smedico.entity.Receta;

@RestController
public class RecetaController {
	
	@Autowired
	private IRecetaService service;
	
	@GetMapping("/receta")
	public List<Receta>findAllReceta(){
		return service.findAll();
	}
	
	@GetMapping("/receta/{idreceta}")
    public Receta findRecetaByid(@PathVariable("idreceta") Long id) {
		return service.findById(id);
		}
	@PostMapping ("/receta")
	public Receta saveReceta(@RequestBody Receta receta) {
		return service.save(receta);
	}
	@PutMapping ("/receta")
	public Receta updateReceta(@RequestBody Receta receta) {
		return service.save(receta);
	}
	@DeleteMapping ("/receta/{idreceta}")
	public String deleteReceta (@PathVariable("idreceta") Long id) {
		service.deleteById(id);
		return "Receta "+id.toString()+ " Eliminada Correctamente";	}
}
