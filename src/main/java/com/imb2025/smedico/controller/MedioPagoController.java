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
		return service.findById(id); //ESTA MAL
	}
	
	@PostMapping //("/")
    public MedioPago createMedioPago(@RequestBody MedioPago mediopago) {
        return service.save(mediopago);
    }
	
	@PutMapping 
	public MedioPago updateMedioPago(@RequestBody MedioPago mediopago) {
		return service.save(mediopago);
	}
	
	
	/*
	@DeleteMapping("/{idmediopago}")
	public String deleteMedioPago(@PathVariable("idmediopago") Long id) {
		service.deleteById(id);
		return "Selección de medio de pago "+id.toString()+ " cancelada.";
	}
	**/
	
	
}  
