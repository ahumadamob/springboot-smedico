package com.imb2025.smedico.controller;


import com.imb2025.smedico.dto.PacienteRequestDTO;

import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.service.IPacienteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pacientes")
public class PacienteController {

    @Autowired
	private IPacienteService pacienteService;

    @GetMapping
    public List<Paciente> getAllPacientes() {
        return pacienteService.findAll();
    }

    @GetMapping("/{id}")
    public Paciente getPacienteById(@PathVariable Long id) {
        return pacienteService.findById(id);
    }
        	

    @PostMapping
    public Paciente createPaciente(@RequestBody Paciente paciente) {
        return pacienteService.save(paciente);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePaciente(@PathVariable Long id, @RequestBody PacienteRequestDTO dto) {
        Paciente existente = pacienteService.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Paciente no encontrado con ID: " + id);
        }

        // Actualizar los campos
        existente.setNombre(dto.getNombre());
        existente.setApellido(dto.getApellido());
        existente.setDni(dto.getDni());
        existente.setEmail(dto.getEmail());
        existente.setFechaNacimiento(dto.getFechaNacimiento());
        existente.setTelefono(dto.getTelefono());

        return ResponseEntity.ok(pacienteService.save(existente));

    public Paciente updatePaciente(@PathVariable Long id, @RequestBody Paciente paciente) {
    	return pacienteService.save(paciente);

    }

    @DeleteMapping("/{id}")
    public void deletePaciente(@PathVariable Long id) {
        pacienteService.deleteById(id);
    }
}