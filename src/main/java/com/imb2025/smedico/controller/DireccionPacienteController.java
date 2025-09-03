package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
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
    public ResponseEntity<ApiResponseSuccessDto<List<DireccionPaciente>>> getAll() {
        List<DireccionPaciente> direcciones = direccionPacienteService.findAll();

        ApiResponseSuccessDto<List<DireccionPaciente>> resp;
        if (direcciones.isEmpty()) {
            resp = new ApiResponseSuccessDto<>(true, "No hay direcciones disponibles", direcciones);
        } else {
            resp = new ApiResponseSuccessDto<>(true, "Lista de direcciones obtenida con éxito", direcciones);
        }
        return ResponseEntity.ok(resp);
    }

    // GET por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DireccionPaciente>> getById(@PathVariable Long id) {
        DireccionPaciente direccion = direccionPacienteService.findById(id);
        ApiResponseSuccessDto<DireccionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Dirección encontrada con éxito", direccion);
        return ResponseEntity.ok(resp);
    }

    // POST crear nueva dirección
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DireccionPaciente>> create(@RequestBody DireccionPacienteRequestDTO dto) {
        DireccionPaciente nuevaDireccion = direccionPacienteService.fromDto(dto);
        DireccionPaciente guardada = direccionPacienteService.save(nuevaDireccion);

        ApiResponseSuccessDto<DireccionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Dirección creada con éxito", guardada);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // PUT actualizar dirección existente
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DireccionPaciente>> update(
            @PathVariable Long id,
            @RequestBody DireccionPacienteRequestDTO dto) {

        DireccionPaciente actualizada = direccionPacienteService.update(id, dto);

        ApiResponseSuccessDto<DireccionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Dirección actualizada con éxito", actualizada);

        return ResponseEntity.ok(resp);
    }

    // DELETE eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable Long id) {
        direccionPacienteService.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Dirección eliminada correctamente", "ID: " + id);
        return ResponseEntity.ok(resp);
    }
}


