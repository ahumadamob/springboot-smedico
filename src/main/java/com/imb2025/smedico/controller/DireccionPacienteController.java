package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.request.DireccionPacienteRequestDTO;
import com.imb2025.smedico.dto.response.DireccionPacienteResponseDTO;
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

    @GetMapping
    public ResponseEntity<List<DireccionPacienteResponseDTO>> getAll() {
        return ResponseEntity.ok(direccionPacienteService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<DireccionPacienteResponseDTO> getById(@PathVariable Long id) {
        // Si no existe, el service lanza ResourceNotFoundException y el GlobalExceptionHandler devuelve 404
        DireccionPacienteResponseDTO dto = direccionPacienteService.findById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<DireccionPacienteResponseDTO> create(@RequestBody DireccionPacienteRequestDTO dto) {
        DireccionPacienteResponseDTO respuesta = direccionPacienteService.crear(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DireccionPacienteResponseDTO> update(@PathVariable Long id,
                                                                @RequestBody DireccionPacienteRequestDTO dto) {
        DireccionPacienteResponseDTO respuesta = direccionPacienteService.update(id, dto);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        direccionPacienteService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}


