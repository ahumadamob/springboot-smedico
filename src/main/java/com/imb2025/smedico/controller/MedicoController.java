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

import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.service.IMedicoService;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class MedicoController {
	
	@Autowired
	private IMedicoService service;
	
	@GetMapping("/medico")
	List<Medico> findallMedicos(){
		return service.findAll();
	}
	
	@GetMapping("/medico/{idmedico}")
		
	Medico findById(@PathVariable("idmedico")Long id) {
		return service.findById(id);
	}
	
	@PostMapping("/medico")
	public Medico create(@RequestBody Medico medico) {
        return service.save(medico);
    }
	
	@PutMapping("/medico")
	 public Medico update(@RequestBody Medico medico) {
        return service.save(medico);
    }
	
	@DeleteMapping("/medico/{idmedico}")
		String deleteMedico(@PathVariable("idmedico")Long id) {
			 service.deleteById(id);
			 return "Medico" + id.toString() + " eliminado correctamente. "; 
		}
	
	
	
}
