package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.service.IDiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    @Autowired
    private IDiagnosticoService service;

    @GetMapping
    public List<Diagnostico> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Diagnostico> getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Diagnostico create(@RequestBody Diagnostico diagnostico) {
        return service.save(diagnostico);
    }

    @PutMapping("/{id}")
    public Diagnostico update(@PathVariable Long id, @RequestBody Diagnostico diagnostico) {
        return service.update(id, diagnostico);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
