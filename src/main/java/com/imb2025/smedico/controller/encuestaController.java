package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.*;
import com.imb2025.smedico.repository.*;
import com.imb2025.smedico.service.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;



@RestController
@RequestMapping("/api/encuestas")
public class encuestaController {

    private final encuestaService service;

    public encuestaController(encuestaService service) {
        this.service = service;
    }

    @GetMapping
    public List<encuestaEntity> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<encuestaEntity> findById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public encuestaEntity save(@RequestBody encuestaEntity encuesta) {
        return service.save(encuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<encuestaEntity> update(@PathVariable Long id, @RequestBody encuestaEntity encuesta) {
        return service.update(id, encuesta)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        if (service.deleteById(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}