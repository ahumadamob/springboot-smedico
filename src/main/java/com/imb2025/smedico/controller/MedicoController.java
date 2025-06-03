package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.MedicoRequestDTO;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.service.IMedicoService;


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
	    public Medico create(@RequestBody MedicoRequestDTO dto) {
	        try {
	            Medico entity = service.fromDto(dto);
	            return service.create(entity);

	        } catch (Exception e) {
	            e.printStackTrace();
	            return null;
	        }
	 }
	    
	
	 @PutMapping("/medico/{idmedico}")
	 public Medico update(@PathVariable("idmedico") Long id,
	                                 @RequestBody MedicoRequestDTO dto) {
	     try {
	         Medico entity = service.fromDto(dto);
	         return service.update(id, entity);
	     } catch (Exception e) {
	    	 e.printStackTrace();
	            return null;
	     }
	 }
	
	@DeleteMapping("/medico/{idmedico}")
	  public ResponseEntity<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Médico con ID " + id + " eliminado correctamente.");
    }
	
	
	
}
