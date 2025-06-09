package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.MedioPagoRequestDTO;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.service.IMedioPagoService;

@RestController
@RequestMapping("/mediopago")
public class MedioPagoController {
	
	@Autowired
	private final IMedioPagoService service;
	
	public MedioPagoController(IMedioPagoService service) {
	    this.service = service;
	}
	
	@GetMapping //("/")
	public List<MedioPago>findAllMedioPago(){
		return service.findAll(); 
	}
	
	@GetMapping("/{idmediopago}")
	public MedioPago findMedioPagoByid(@PathVariable("idmediopago") Long id) {
		return service.findById(id); 
	}
	
	@PostMapping //("/")
    public MedioPago createMedioPago(@RequestBody MedioPagoRequestDTO mediopagoDto) {
		try {
	        return service.createMedioPago(mediopagoDto);
	    } catch (Exception e) {
	        System.out.println("Error al crear MedioPago: " + e.getMessage());
	        return null; 
	    }
    }
	
	@PutMapping 
	public MedioPago updateMedioPago(@RequestBody MedioPago mediopago) {
		return service.save(mediopago);
	}
	
	
}  
