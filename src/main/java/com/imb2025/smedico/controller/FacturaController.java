package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.FacturaRequestDTO;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.service.IFacturaService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Factura createFactura(@RequestBody FacturaRequestDTO requestDTO){
        try {
            return facturaService.create(facturaService.fromDto(requestDTO));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @PutMapping("/{id}")
    public Factura updateFactura(@PathVariable Long id, @RequestBody FacturaRequestDTO requestDTO){
        try{
            return facturaService.update(id, facturaService.fromDto(requestDTO));
        } catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    @DeleteMapping("/{id}")
    public String deleteFactura(@PathVariable Long id){
        facturaService.deleteById(id);
        return "La factura con el ID: " + id + "fue eliminado correctamente.";
    }
}
