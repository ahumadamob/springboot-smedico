package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.DireccionPacienteRequestDTO;
import com.imb2025.smedico.entity.DireccionPaciente;
import com.imb2025.smedico.service.DireccionPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/direccion-paciente")
public class DireccionPacienteController {

    @Autowired
    private DireccionPacienteService direccionPacienteService;

    // GET todos
    @GetMapping
    public ResponseEntity<List<DireccionPaciente>> getAll() {
        List<DireccionPaciente> direcciones = direccionPacienteService.findAll();
        return ResponseEntity.ok(direcciones);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<DireccionPaciente> getById(@PathVariable Long id) {
        return direccionPacienteService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // POST crear nueva dirección
    @PostMapping
    public ResponseEntity<?> create(@RequestBody DireccionPacienteRequestDTO dto) {
        try {
            DireccionPaciente nuevaDireccion = direccionPacienteService.fromDto(dto);
            DireccionPaciente guardada = direccionPacienteService.save(nuevaDireccion);
            return ResponseEntity.status(HttpStatus.CREATED).body(guardada);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al crear la dirección: " + e.getMessage());
        }
    }

    // PUT actualizar dirección existente
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id,
                                    @RequestBody DireccionPacienteRequestDTO dto) {
        try {
            if (!direccionPacienteService.existePorId(id)) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("No se encontró la dirección con ID: " + id);
            }

            DireccionPaciente actualizada = direccionPacienteService.update(id, dto);
            return ResponseEntity.ok(actualizada);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar la dirección: " + e.getMessage());
        }
    }

    // DELETE eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (!direccionPacienteService.existePorId(id)) {
            return ResponseEntity.notFound().build();
        }
        direccionPacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


