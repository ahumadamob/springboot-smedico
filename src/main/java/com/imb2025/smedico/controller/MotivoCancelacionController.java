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

import com.imb2025.smedico.service.IMotivoCancelacionService;
import com.imb2025.smedico.entity.MotivoCancelacion;

@RestController
public class MotivoCancelacionController {
	
	@Autowired
	private IMotivoCancelacionService service;
	
	@GetMapping("/motivocancelacion")
	public List<MotivoCancelacion>findAllMotivoCancelacion(){
		return service.findAll();
	}
	
	@GetMapping("/motivocancelacion/{idmotivocancelacion}")
	public MotivoCancelacion findMotivoCancelacionByid(@PathVariable("idmotivocancelacion") Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/motivocancelacion")
	public MotivoCancelacion saveMotivoCancelacion(@RequestBody MotivoCancelacion motivocancelacion) {
		return service.save(motivocancelacion);
	}
	
	@PutMapping("/motivocancelacion")
	public MotivoCancelacion updateMotivoCancelacion(@RequestBody MotivoCancelacion motivocancelacion) {
		return service.save(motivocancelacion);
	}
	
	@DeleteMapping("/motivocancelacion/{idmotivocancelacion}")
	public String deleteMotivoCancelacion(@PathVariable("idmotivocancelacion") Long id) {
		service.deleteById(id);
		return "Motivo de Cancelacion "+id.toString()+ " Eliminado Correctamente";
	}
}
