package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.RecetaMedicamento;
import com.imb2025.smedico.service.RecetaMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recetamedicamento")
public class RecetaMedicamentoController {

    @Autowired
    private RecetaMedicamentoService service;

    @GetMapping
    public List<RecetaMedicamento> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecetaMedicamento> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public RecetaMedicamento save(@RequestBody RecetaMedicamento recetaMedicamento) {
        return service.save(recetaMedicamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RecetaMedicamento> update(@PathVariable Long id, @RequestBody RecetaMedicamento recetaMedicamento) {
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
