package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.IDetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleReceta")
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService service;

    @GetMapping
    public List<DetalleReceta> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public DetalleReceta findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public DetalleReceta save(@RequestBody DetalleReceta recetaMedicamento) {
        return service.save(recetaMedicamento);
    }

    @PutMapping("/{id}")
    public DetalleReceta update(@PathVariable Long id, @RequestBody DetalleReceta recetaMedicamento) {
    	return service.save(recetaMedicamento);

    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        service.deleteById(id);
    }
}
