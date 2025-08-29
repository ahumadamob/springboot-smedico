package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseDTO;
import com.imb2025.smedico.dto.DireccionPacienteRequestDTO;
import com.imb2025.smedico.entity.DireccionPaciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.service.DireccionPacienteService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/direcciones")
public class DireccionPacienteController {
	
	public DireccionPacienteController(DireccionPacienteService direccionPacienteService) {
    }
    
    @Autowired
    private DireccionPacienteService direccionPacienteService;

    // GET todos
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<DireccionPaciente>>> getAll() {
        List<DireccionPaciente> direcciones = direccionPacienteService.findAll();

        ApiResponseDTO<List<DireccionPaciente>> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setData(direcciones);
        response.setMessage("Lista de direcciones obtenida con éxito");

        return ResponseEntity.ok(response);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<DireccionPaciente>> getById(@PathVariable Long id) {
        DireccionPaciente direccion = direccionPacienteService.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("No existe dirección con id " + id));

        ApiResponseDTO<DireccionPaciente> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setData(direccion);
        response.setMessage("Dirección encontrada con éxito");

        return ResponseEntity.ok(response);
    }

    // POST crear nueva dirección
    @PostMapping
    public ResponseEntity<ApiResponseDTO<DireccionPaciente>> create(
            @Valid @RequestBody DireccionPacienteRequestDTO dto) {

        DireccionPaciente nuevaDireccion = direccionPacienteService.fromDto(dto);
        DireccionPaciente guardada = direccionPacienteService.save(nuevaDireccion);

        ApiResponseDTO<DireccionPaciente> response = new ApiResponseDTO<>();
        response.setSuccess(true);
        response.setData(guardada);
        response.setMessage("Dirección creada con éxito");

     // devolver con codigo 201
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
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


