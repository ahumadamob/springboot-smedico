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

import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.service.IResultadoEstudioService;

@RestController
public class ResultadoEstudioController {

	@Autowired
	private IResultadoEstudioService service;
	
	@GetMapping("/ResultadoEstudio")
	public List<ResultadoEstudio>findAll(){
		return service.findAll();
	}
	
	@GetMapping("/ResultadoEstudio/{idResultadoEstudio}")
	public ResultadoEstudio findById(@PathVariable("idResultadoEstudio") long id) {
		return service.findById(id);
	}
	
	@PostMapping("/ResultadoEstudio")
	public ResultadoEstudio createResultadoEstudio(@RequestBody ResultadoEstudio resultadoEstudio) {
		return service.save(resultadoEstudio);
		
	}
	
	
	@PutMapping ("/ResultadoEstudio")
	public ResultadoEstudio  updateResultadoEstudio(@RequestBody ResultadoEstudio resultadoEstudio) {
		return service.save(resultadoEstudio);
	}
	
	@DeleteMapping ("/ResultadoEstudio/{idResultadoEstudio}")
	public String deleteResultadoEstudio (@PathVariable("idResultadoEstudio") Long id) {
		service.deleteById(id);
		return "Estudio "+id.toString()+ " Eliminado Correctamente";	}
	
	
}
