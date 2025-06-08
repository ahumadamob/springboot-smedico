package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.IDetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/detalleReceta")  
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService service;


    @GetMapping
    public ResponseEntity<List<DetalleReceta>> findAll() {
        List<DetalleReceta> list = service.findAll();
        return ResponseEntity.ok(list);
    }

   
    @GetMapping("/{id}")
    public ResponseEntity<DetalleReceta> findById(@PathVariable Long id) {
        DetalleReceta detalleReceta = service.findById(id);
        return ResponseEntity.ok(detalleReceta);
    }

    @PostMapping
    public ResponseEntity<DetalleReceta> create(@RequestBody DetalleReceta detalleReceta) {
        DetalleReceta nuevo = service.create(detalleReceta);
        return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
    }


    @PutMapping("/{id}")
    public ResponseEntity<DetalleReceta> update(@PathVariable Long id, @RequestBody DetalleReceta detalleReceta) {
        DetalleReceta actualizado = service.update(id, detalleReceta);
        return ResponseEntity.ok(actualizado);
    }

  
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}