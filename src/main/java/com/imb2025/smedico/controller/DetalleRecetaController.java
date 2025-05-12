package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.DetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleReceta")
public class DetalleRecetaController {

    @Autowired
    private DetalleRecetaService service;

    @GetMapping
    public List<DetalleReceta> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleReceta> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public DetalleReceta save(@RequestBody DetalleReceta recetaMedicamento) {
        return service.save(recetaMedicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleReceta> update(@PathVariable Long id, @RequestBody DetalleReceta recetaMedicamento) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(service.update(id, recetaMedicamento));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (!service.findById(id).isPresent()) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
