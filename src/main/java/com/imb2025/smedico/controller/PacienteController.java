package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.PacienteRequestDto;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.service.IPacienteService;

import jakarta.validation.Valid;

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
    public ResponseEntity<ApiResponseSuccessDto<List<Paciente>>> getAllPacientes() {
        List<Paciente> pacientes = pacienteService.findAll();
        ApiResponseSuccessDto<List<Paciente>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(pacientes);
        resp.setMessage(pacientes.isEmpty() ? "No hay pacientes registrados" : "Lista de pacientes");
        return ResponseEntity.ok(resp);
    }

    // Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Paciente>> getPacienteById(@PathVariable Long id) {
        Paciente paciente = pacienteService.findById(id); // Lanza ResourceNotFoundException si no existe

        ApiResponseSuccessDto<Paciente> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(paciente);
        resp.setMessage("Paciente encontrado correctamente");

        return ResponseEntity.ok(resp);
    }

    // Crear paciente
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Paciente>> createPaciente(
            @Valid @RequestBody PacienteRequestDto dto) {
        Paciente entidad = pacienteService.fromDto(dto);
        Paciente nuevo = pacienteService.create(entidad);

        ApiResponseSuccessDto<Paciente> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(nuevo);
        resp.setMessage("Paciente creado correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Paciente>> updatePaciente(
            @PathVariable Long id, @Valid @RequestBody PacienteRequestDto dto) {

        Paciente entidad = pacienteService.fromDto(dto);
        Paciente actualizado = pacienteService.update(id, entidad);

        ApiResponseSuccessDto<Paciente> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(actualizado);
        resp.setMessage("Paciente actualizado correctamente");
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deletePaciente(@PathVariable Long id) {
        pacienteService.deleteById(id);

        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setMessage("Paciente eliminado correctamente");
        return ResponseEntity.ok(resp);
    }

}
