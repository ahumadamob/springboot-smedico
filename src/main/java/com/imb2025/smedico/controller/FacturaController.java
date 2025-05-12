package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/factura")
public class FacturaController {

    @Autowired
    private IFacturaService facturaService;

    @GetMapping
    public ResponseEntity<List<Factura>> findAll(){
        List<Factura> facturas = facturaService.findAll();
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> findById(@PathVariable Long id){
        Factura factura = facturaService.findById(id);
        return ResponseEntity.ok(factura);
    }

    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody Factura factura){
        return ResponseEntity.ok(facturaService.save(factura));
    }

    @PutMapping
    public ResponseEntity<Factura> updateFactura(@RequestBody Factura factura){
        return ResponseEntity.ok(facturaService.save(factura));
    }

    @DeleteMapping("/{id}")
    public String deleteFactura(@PathVariable Long id){
        facturaService.deleteById(id);
        return "La factura con el ID: " + id + "fue eliminado correctamente.";
    }
}
