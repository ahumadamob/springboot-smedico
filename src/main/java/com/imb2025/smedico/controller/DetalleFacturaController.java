




package com.imb2025.smedico.controller;

import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.service.IDetalleFacturaService;

import com.imb2025.smedico.entity.DetalleFactura;

import com.imb2025.smedico.dto.DetalleFacturaRequestDTO;


import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;

@RestController
public class DetalleFacturaController {

    private final IDetalleFacturaService detalleFacturaService;
    public DetalleFacturaController(IDetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping("/detallefactura")
    public ResponseEntity<List<DetalleFactura>> findAll() {
        return ResponseEntity.ok(detalleFacturaService.findAll());
    }

    @GetMapping("/detallefactura/{id}")
    public ResponseEntity<DetalleFactura> findById(@PathVariable Long id) {
        DetalleFactura detalle = detalleFacturaService.findById(id);
        if (detalle == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping("/detallefactura")
    public ResponseEntity<DetalleFactura> create(@RequestBody DetalleFacturaRequestDTO dto) throws Exception {
        DetalleFactura detalle = detalleFacturaService.fromDto(dto);
        DetalleFactura creado = detalleFacturaService.create(detalle);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/detallefactura/{id}")
    public ResponseEntity<DetalleFactura> update(@PathVariable Long id, @RequestBody DetalleFacturaRequestDTO dto) throws Exception {
        if (!detalleFacturaService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        DetalleFactura actualizado = detalleFacturaService.fromDto(dto, id);
        DetalleFactura saved = detalleFacturaService.update(id, actualizado);
        return ResponseEntity.ok(saved);
    }

    @DeleteMapping("/detallefactura/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        detalleFacturaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
