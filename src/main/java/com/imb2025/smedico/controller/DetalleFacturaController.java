




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

@RestController
public class DetalleFacturaController {

    private final IDetalleFacturaService detalleFacturaService;
    public DetalleFacturaController(IDetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }
   
    @GetMapping("/detallefactura")
    public List<DetalleFactura> findAll() {
        return detalleFacturaService.findAll();
    }
    @GetMapping("/detallefactura/{id}") 
    public DetalleFactura findById(@PathVariable Long id) {
        return detalleFacturaService.findById(id);
    }

    @PostMapping("/detallefactura")
public DetalleFactura create(@RequestBody DetalleFacturaRequestDTO dto) throws Exception {
    DetalleFactura detalle = detalleFacturaService.fromDto(dto);
    return detalleFacturaService.save(detalle);
}

    @PutMapping("/detallefactura")
    public DetalleFactura update(@RequestBody DetalleFactura detalleFactura) {
        return detalleFacturaService.save(detalleFactura);
    }
    @PutMapping("/detallefactura/{id}")
    public DetalleFactura update(@PathVariable Long id, @RequestBody DetalleFacturaRequestDTO dto) throws Exception {
        DetalleFactura existente = detalleFacturaService.findById(id);
        if (existente == null) {
            throw new RuntimeException("DetalleFactura con ID " + id + " no existe.");
        }
        DetalleFactura actualizado = detalleFacturaService.fromDto(dto, id);
        return detalleFacturaService.save(actualizado);
    }
    
    @DeleteMapping("/detallefactura/{id}")
    public void delete(@PathVariable Long id) {
        detalleFacturaService.deleteById(id);
    }
}
