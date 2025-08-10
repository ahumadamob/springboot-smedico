package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.AfiliacionRequestDTO;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/afiliacion")
public class AfiliacionController {

    @Autowired
    private IAfiliacionService servi;

    @GetMapping
    public ResponseEntity<List<Afiliacion>> findAllAfiliacion() {
        List<Afiliacion> lista = servi.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afiliacion> findAfiliacionById(@PathVariable("id") Long idAfiliacion) {
        Afiliacion af = servi.findById(idAfiliacion);
        if (af == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(af);
    }

    @PostMapping
    public ResponseEntity<Afiliacion> createAfiliacion(@RequestBody AfiliacionRequestDTO dto) throws Exception {
        Afiliacion afiliacion = servi.fromDto(dto);
        Afiliacion creada = servi.create(afiliacion);
        return ResponseEntity.ok(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Afiliacion> updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDTO dto) throws Exception {
        Afiliacion afiliacion = servi.fromDto(dto);
        Afiliacion actualizada = servi.update(id, afiliacion);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAfiliacion(@PathVariable Long id) {
        servi.deleteById(id);
        return ResponseEntity.ok("Afiliación " + id + " eliminada correctamente.");
    }

    // Manejo global de errores
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
}

