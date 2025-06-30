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

import com.imb2025.smedico.service.IRecetaService;
import com.imb2025.smedico.dto.RecetaRequestDTO;
import com.imb2025.smedico.entity.Receta;

@RestController
public class RecetaController {
	
	@Autowired
	private IRecetaService service;
	
    @GetMapping("/receta")
    public ResponseEntity<List<Receta>> findAllReceta() {
        List<Receta> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
        
    }
	
	@GetMapping("/receta/existe/{id}")
	public ResponseEntity<Void> existsById(@PathVariable("id") Long id) {
	    if (service.existsById(id)) {
	        return ResponseEntity.ok().build();       
	    } else {
	        return ResponseEntity.notFound().build();
	    }
	}
	@PostMapping("/receta")
	public ResponseEntity<Receta> create(@RequestBody RecetaRequestDTO recetaRequestDTO) throws Exception {
        Receta nueva = service.create(service.fromDto(recetaRequestDTO));
        return ResponseEntity.ok(nueva);
    }
	
	@PutMapping("/receta/{idreceta}")
	public ResponseEntity<Receta> update(@PathVariable("idreceta") Long id,
	        @RequestBody RecetaRequestDTO recetaRequestDTO) throws Exception {
	    Receta entity = service.fromDto(recetaRequestDTO);
	    return ResponseEntity.ok(service.update(id, entity));
	}
	
	@DeleteMapping ("/receta/{idreceta}")
	public ResponseEntity<String> deleteReceta(@PathVariable("idreceta") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Receta " + id + " eliminada correctamente.");
    }
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex) {
	  return ResponseEntity.badRequest().body(ex.getMessage());
	}
	
}



