package com.imb2025.smedico.controller;

//import com.imb2025.smedico.dto.DireccionPacienteRequestDTO;
import com.imb2025.smedico.entity.DireccionPaciente;
import com.imb2025.smedico.service.DireccionPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
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
        List<DireccionPaciente> lista = direccionPacienteService.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(lista); // 200
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
    public ResponseEntity<DireccionPaciente> create(@RequestBody DireccionPaciente direccion) {
        DireccionPaciente saved = direccionPacienteService.save(direccion);
        return ResponseEntity.ok(saved); // 200
    }

    // PUT actualizar dirección existente
    @PutMapping("/{id}")
    public ResponseEntity<DireccionPaciente> update(@PathVariable Long id,
                                                    @RequestBody DireccionPaciente direccionPaciente) {
        DireccionPaciente updated = direccionPacienteService.update(id, direccionPaciente);
        return ResponseEntity.ok(updated); // 200 OK
    }

    // DELETE eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        direccionPacienteService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    // Manejo de errores
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage()); // 400
      }
    
    }



