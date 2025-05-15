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
    public List<Factura> findAll(){
        return facturaService.findAll();
    }

    @GetMapping("/{id}")
    public Factura findById(@PathVariable Long id){
        return facturaService.findById(id);
    }

    @PostMapping
    public Factura createFactura(@RequestBody Factura factura){
        return facturaService.save(factura);
    }

    @PutMapping
    public Factura updateFactura(@RequestBody Factura factura){
        return facturaService.save(factura);
    }

    @DeleteMapping("/{id}")
    public String deleteFactura(@PathVariable Long id){
        facturaService.deleteById(id);
        return "La factura con el ID: " + id + "fue eliminado correctamente.";
    }
}
