package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.FacturaRequestDTO;
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
        if(facturas.isEmpty()){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(facturas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Factura> findById(@PathVariable Long id){
        Factura factura = facturaService.findById(id);
        if(factura == null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(factura);
    }

    @PostMapping
    public ResponseEntity<Factura> createFactura(@RequestBody FacturaRequestDTO requestDTO) throws Exception{
        return ResponseEntity.ok(facturaService.create(facturaService.fromDto(requestDTO)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Factura> updateFactura(@PathVariable Long id, @RequestBody FacturaRequestDTO requestDTO) throws Exception{
        return ResponseEntity.ok(facturaService.update(id, facturaService.fromDto(requestDTO)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteFactura(@PathVariable Long id) throws Exception{
        facturaService.deleteById(id);
        return ResponseEntity.ok("La factura con el ID: " + id + "fue eliminado correctamente.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e){
        return ResponseEntity.badRequest().body(e.getMessage());
    }

}
