package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
	public ResponseEntity<List<MotivoCancelacion>> findAllMotivoCancelacion() {
        List<MotivoCancelacion> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
	}
	
	@GetMapping("/motivocancelacion/{idmotivocancelacion}")
	public ResponseEntity<MotivoCancelacion> findMotivoCancelacionById(@PathVariable("idmotivocancelacion") Long id) {
        MotivoCancelacion motivoCancelacion = service.findById(id);
        if (motivoCancelacion == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(motivoCancelacion);
	}
	
	@PostMapping("/motivocancelacion")
	public ResponseEntity<MotivoCancelacion> create(@RequestBody MotivoCancelacionRequestDTO dto) throws Exception {
        return ResponseEntity.ok(service.create(service.fromDto(dto)));
    }
		
	@PutMapping("/motivocancelacion/{idmotivocancelacion}")
    public ResponseEntity<MotivoCancelacion> update(@PathVariable("idmotivocancelacion") Long id,@RequestBody MotivoCancelacionRequestDTO dto) throws Exception {
        MotivoCancelacion entity = service.fromDto(dto);
        return ResponseEntity.ok(service.update(id, entity));
    }
	
	@DeleteMapping("/motivocancelacion/{idmotivocancelacion}")
	public ResponseEntity<String> deleteMotivoCancelacion(@PathVariable("idmotivocancelacion") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Motivo de Cancelación " + id + " eliminado correctamente.");
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalExceptions(Exception ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
