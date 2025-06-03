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
import com.imb2025.smedico.dto.MotivoCancelacionRequestDTO;
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
    public MotivoCancelacion create(@RequestBody MotivoCancelacionRequestDTO dto) {
        try {
            MotivoCancelacion entity = service.fromDto(dto);
            return service.create(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
		
	@PutMapping("/motivocancelacion/{idmotivocancelacion}")
    public MotivoCancelacion update(@PathVariable("idmotivocancelacion") Long id,
                                    @RequestBody MotivoCancelacionRequestDTO dto) {
        try {
            MotivoCancelacion entity = service.fromDto(dto);
            return service.update(id, entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
	
	@DeleteMapping("/motivocancelacion/{idmotivocancelacion}")
    public String deleteMotivoCancelacion(@PathVariable("idmotivocancelacion") Long id) {
        service.deleteById(id);
        return "Motivo de Cancelacion " + id + " Eliminado Correctamente";
    }
}
