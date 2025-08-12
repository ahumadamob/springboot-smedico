package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.DireccionPacienteRequestDto;
import com.imb2025.smedico.entity.DireccionPaciente;
import com.imb2025.smedico.service.IDireccionPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion-paciente")
public class DireccionPacienteController {

    @Autowired
    private IDireccionPacienteService direccionPacienteService;

    @GetMapping
    public ResponseEntity<List<DireccionPaciente>> getAll() {
        List<DireccionPaciente> lista = direccionPacienteService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionPaciente> getById(@PathVariable Long id) {
        DireccionPaciente direccion = direccionPacienteService.findById(id);
        return direccion != null ? ResponseEntity.ok(direccion) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<DireccionPaciente> create(@RequestBody DireccionPacienteRequestDto direccionPacienteRequestDto) throws Exception {
        DireccionPaciente direccion = direccionPacienteService.fromDto(direccionPacienteRequestDto);
        DireccionPaciente saved = direccionPacienteService.create(direccion);
        return ResponseEntity.ok(saved);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionPaciente> update(@PathVariable Long id,
                                                    @RequestBody DireccionPacienteRequestDto direccionPacienteRequestDto) throws Exception {
        DireccionPaciente direccion = direccionPacienteService.fromDto(direccionPacienteRequestDto);
        DireccionPaciente updated = direccionPacienteService.update(id, direccion);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        direccionPacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
}
