package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.DireccionPacienteRequestDTO;
import com.imb2025.smedico.entity.DireccionPaciente;
import com.imb2025.smedico.service.DireccionPacienteService;

import jakarta.validation.Valid;
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
    public ResponseEntity<ApiResponseSuccessDto<List<DireccionPaciente>>> getAll() {
        List<DireccionPaciente> direcciones = direccionPacienteService.findAll();
        ApiResponseSuccessDto<List<DireccionPaciente>> resp =
                new ApiResponseSuccessDto<>(direcciones, "Direcciones encontradas con éxito");
        return ResponseEntity.ok(resp);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DireccionPaciente>> getById(@PathVariable Long id) {
        DireccionPaciente direccion = direccionPacienteService.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró la dirección con ID: " + id));

        ApiResponseSuccessDto<DireccionPaciente> resp =
                new ApiResponseSuccessDto<>(direccion, "Dirección encontrada con éxito");
        return ResponseEntity.ok(resp);
    }

    // POST crear nueva dirección
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DireccionPaciente>> create(
            @Valid @RequestBody DireccionPacienteRequestDTO dto) {

        DireccionPaciente nuevaDireccion = direccionPacienteService.fromDto(dto);
        DireccionPaciente guardada = direccionPacienteService.save(nuevaDireccion);

        ApiResponseSuccessDto<DireccionPaciente> resp =
                new ApiResponseSuccessDto<>(guardada, "Dirección creada con éxito");
        return ResponseEntity.status(201).body(resp);
    }

    // PUT actualizar dirección existente
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DireccionPaciente>> update(
            @PathVariable Long id,
            @Valid @RequestBody DireccionPacienteRequestDTO dto) {

        DireccionPaciente actualizada = direccionPacienteService.update(id, dto);

        ApiResponseSuccessDto<DireccionPaciente> resp =
                new ApiResponseSuccessDto<>(actualizada, "Dirección actualizada con éxito");
        return ResponseEntity.ok(resp);
    }

    // DELETE eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        direccionPacienteService.deleteById(id);

        ApiResponseSuccessDto<Void> resp =
                new ApiResponseSuccessDto<>(null, "Dirección eliminada con éxito");
        return ResponseEntity.ok(resp);
    }
}

