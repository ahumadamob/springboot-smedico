package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.PacienteRequestDTO;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.service.IPacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    @GetMapping
    public ResponseEntity<List<Paciente>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        if (pacientes.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(pacientes); // 200
    }

    @GetMapping("/{id}")
    public ResponseEntity<Paciente> getPacienteById(@PathVariable Long id) {
        Paciente paciente = pacienteService.findById(id);
        if (paciente == null) {
            return ResponseEntity.noContent().build(); // 204
        }
        return ResponseEntity.ok(paciente); // 200
    }

    @PostMapping
    public ResponseEntity<Paciente> createPaciente(@RequestBody Paciente paciente) {
        Paciente nuevo = pacienteService.save(paciente);
        return ResponseEntity.ok(nuevo); // 200
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable Long id, @RequestBody PacienteRequestDTO dto) {
        Paciente existente = pacienteService.findById(id);
        if (existente == null) {
            return ResponseEntity.badRequest().body("Paciente no encontrado con ID: " + id); // 400
        }

        existente.setNombre(dto.getNombre());
        existente.setApellido(dto.getApellido());
        existente.setDni(dto.getDni());
        existente.setEmail(dto.getEmail());
        existente.setFechaNacimiento(dto.getFechaNacimiento());
        existente.setTelefono(dto.getTelefono());

        return ResponseEntity.ok(pacienteService.save(existente)); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        if (!pacienteService.existsById(id)) {
            return ResponseEntity.badRequest().body("No se puede eliminar: Paciente no encontrado con ID: " + id); // 400
        }
        pacienteService.deleteById(id);
        return ResponseEntity.ok().build(); // 200
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage()); // 400
    }
}