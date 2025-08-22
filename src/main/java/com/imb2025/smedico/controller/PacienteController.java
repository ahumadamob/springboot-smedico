package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseDTO;
import com.imb2025.smedico.dto.PacienteRequestDto;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService pacienteService;

    // Listar todos los pacientes
    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<Paciente>>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        ApiResponseDTO<List<Paciente>> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(pacientes);
        resp.setMessage(pacientes.isEmpty() ? "No hay pacientes registrados" : "Lista de pacientes");
        return ResponseEntity.ok(resp);
    }

    // Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Paciente>> getPacienteById(@PathVariable Long id) {
        Paciente paciente = pacienteService.findById(id); // Lanza ResourceNotFoundException si no existe

        ApiResponseDTO<Paciente> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(paciente);
        resp.setMessage("Paciente encontrado correctamente");

        return ResponseEntity.ok(resp);
    }

    // Crear paciente
    @PostMapping
    public ResponseEntity<ApiResponseDTO<Paciente>> createPaciente(@RequestBody PacienteRequestDto dto) {
        Paciente entidad = pacienteService.fromDto(dto);
        Paciente nuevo = pacienteService.create(entidad);

        ApiResponseDTO<Paciente> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(nuevo);
        resp.setMessage("Paciente creado correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Paciente>> updatePaciente(@PathVariable Long id,
                                                                   @RequestBody PacienteRequestDto dto) {
        if (!pacienteService.existsById(id)) {
            throw new ResourceNotFoundException("Paciente no encontrado con ID: " + id);
        }

        Paciente entidad = pacienteService.fromDto(dto);
        Paciente actualizado = pacienteService.update(id, entidad);

        ApiResponseDTO<Paciente> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(actualizado);
        resp.setMessage("Paciente actualizado correctamente");

        return ResponseEntity.ok(resp);
    }

    // Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deletePaciente(@PathVariable Long id) {
        if (!pacienteService.existsById(id)) {
            throw new ResourceNotFoundException("No se puede eliminar: Paciente no encontrado con ID: " + id);
        }
        pacienteService.deleteById(id);

        ApiResponseDTO<Void> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(null);
        resp.setMessage("Paciente eliminado correctamente");

        return ResponseEntity.ok(resp);
    }
}
