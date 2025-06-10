package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.OrdenEstudioRequestDTO;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.service.IOrdenEstudioService;

import jakarta.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/ordenestudio")
public class OrdenEstudioController {

    @Autowired
    private IOrdenEstudioService service;

    // GET - Obtener todas las órdenes de estudio
    @GetMapping
    public ResponseEntity<List<OrdenEstudio>> findAllOrdenEstudio() {
        List<OrdenEstudio> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(lista); 
    }

    // GET - Obtener una orden de estudio por ID
    @GetMapping("/{id}")
    public ResponseEntity<OrdenEstudio> findOrdenEstudioById(@PathVariable("id") Long id) {
        OrdenEstudio orden = service.findById(id);
        if (orden == null) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(orden); 
    }

    // POST - Crear una nueva orden de estudio
    @PostMapping
    public ResponseEntity<OrdenEstudio> createOrdenEstudio(@RequestBody OrdenEstudioRequestDTO dto) throws Exception {
        OrdenEstudio entity = service.fromDto(dto);
        return ResponseEntity.ok(service.create(entity)); 
    }

    // PUT - Actualizar una orden de estudio
    @PutMapping("/{id}")
    public ResponseEntity<OrdenEstudio> updateOrdenEstudio(@PathVariable("id") Long id, @RequestBody OrdenEstudioRequestDTO dto) throws Exception {
        OrdenEstudio entity = service.fromDto(dto);
        return ResponseEntity.ok(service.update(id, entity)); 
    }

    // DELETE - Eliminar una orden de estudio
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrdenEstudio(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Orden de estudio " + id + " eliminada correctamente."); 
    }

    // Manejador de excepciones específicas
    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<String> handleNotFound(EntityNotFoundException ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage()); // 400 Bad Request
    }

    // Manejador global de excepciones
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Excepción general: " + ex.getMessage()); // 400 Bad Request
    }
}