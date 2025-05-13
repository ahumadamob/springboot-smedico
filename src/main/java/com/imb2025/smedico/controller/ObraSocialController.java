package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.service.IObraSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/obrasocial")
public class ObraSocialController {

    @Autowired
    private IObraSocialService service;

    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ObraSocial> obras = service.findAll();

        if (!obras.isEmpty()) {
            return ResponseEntity.ok(obras);
        } else {
            return ResponseEntity
                    .status(200)
                    .body("Sin datos: no hay obras sociales registradas.");
        }
    }


    @GetMapping("/{id}")
    public ResponseEntity<ObraSocial> getById(@PathVariable Long id) {
        return service.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ObraSocial create(@RequestBody ObraSocial obraSocial) {
        return service.save(obraSocial);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ObraSocial> update(@PathVariable Long id, @RequestBody ObraSocial obraSocial) {
        ObraSocial updated = service.update(id, obraSocial);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

