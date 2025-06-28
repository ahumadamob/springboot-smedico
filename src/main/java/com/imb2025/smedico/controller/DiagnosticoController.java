package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.service.IDiagnosticoService;
import com.imb2025.smedico.dto.DiagnosticoRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    @Autowired
    private IDiagnosticoService service;

    // Lista todos
    @GetMapping
    public ResponseEntity<List<Diagnostico>> getAll() {
        List<Diagnostico> diagnosticos = service.findAll();
        return ResponseEntity.ok(diagnosticos); // 200 OK
    }

    // Busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> getById(@PathVariable Long id) {
        Diagnostico diagnostico = service.findById(id);
        if (diagnostico == null) {
            return ResponseEntity.notFound().build(); // 404 NOT FOUND
        }
        return ResponseEntity.ok(diagnostico); // 200 OK
    }

    // Crea nuevo
    @PostMapping("/diagnosticos")
    public ResponseEntity<?> crearDiagnostico(@RequestBody DiagnosticoRequestDTO dto) {
        Diagnostico nuevo = service.fromDto(dto);
        Diagnostico guardado = service.save(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado); // 201 CREATED
    }

    // Actualiza el existente
    @PutMapping("/diagnosticos/{id}")
    public ResponseEntity<Diagnostico> actualizarDiagnostico(@PathVariable Long id,
                                                             @RequestBody DiagnosticoRequestDTO dto) {
        Diagnostico diagnostico = service.fromDto(dto);
        Diagnostico actualizado = service.actualizar(id, diagnostico);
        return ResponseEntity.ok(actualizado); // 200 OK
    }

    // Elimina por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 NO CONTENT
    }

    // Manejador de excepciones centralizado
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage()); // 400 BAD REQUEST
    }
}

