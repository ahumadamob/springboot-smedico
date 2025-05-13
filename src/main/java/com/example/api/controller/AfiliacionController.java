package com.example.api.controller;

import com.example.api.entity.Afiliacion;

import com.example.api.services.AfiliacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/afiliaciones")
public class AfiliacionController {

    @Autowired
    private AfiliacionService service;

    @GetMapping
    public List<Afiliacion> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afiliacion> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Afiliacion create(@RequestBody Afiliacion afiliacion) {
        return (Afiliacion) service.save(afiliacion);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> update(@PathVariable Long id, @RequestBody Afiliacion afiliacion) {
        try {
            return ResponseEntity.ok(service.update(id, afiliacion));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}