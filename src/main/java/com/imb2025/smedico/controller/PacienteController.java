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
    
    // Listar todos los pacientes
    @GetMapping("/porapellido")
    public ResponseEntity<ApiResponseSuccessDto<List<Paciente>>> getAllPacientesOrdenados() {
        List<Paciente> pacientes = pacienteService.findAllOrder();
        ApiResponseSuccessDto<List<Paciente>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(pacientes);
        resp.setMessage(pacientes.isEmpty() ? "No hay pacientes registrados" : "Lista de pacientes");
        return ResponseEntity.ok(resp);
    } 
    
    @GetMapping("/dni/{numeroDni}")
    public ResponseEntity<ApiResponseSuccessDto<List<Paciente>>> getPacienteByDni(@PathVariable String numeroDni) {
        List<Paciente> pacientes = pacienteService.findByDni(numeroDni);
        ApiResponseSuccessDto<List<Paciente>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(pacientes);
        resp.setMessage(pacientes.isEmpty() ? "No hay pacientes registrados" : "Lista de pacientes");
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/domain/{domainValue}")
    public ResponseEntity<ApiResponseSuccessDto<List<Paciente>>> getPacienteByDomainEmial(@PathVariable String domainValue) {
        List<Paciente> pacientes = pacienteService.findByDomainEmail(domainValue);
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
    
    @GetMapping("/cantidad")
    public ResponseEntity<ApiResponseSuccessDto<Long>> getCountPacientes() {
        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(pacienteService.countBy());
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
    public ResponseEntity<?> updatePaciente(@PathVariable Long id,@Valid @RequestBody PacienteRequestDto dto) {
        if (!pacienteService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Paciente no encontrado con ID: " + id);
        }
        try {
            Paciente entidad = pacienteService.fromDto(dto);
            Paciente actualizado = pacienteService.update(id, entidad);
            return ResponseEntity.ok(actualizado);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePaciente(@PathVariable Long id) {
        if (!pacienteService.existsById(id)) {
            return ResponseEntity.badRequest()
                    .body("No se puede eliminar: Paciente no encontrado con ID: " + id);
        }
        pacienteService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
