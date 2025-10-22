package com.imb2025.smedico.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.PacienteRequestDto;
import com.imb2025.smedico.dto.response.PacienteResponseDto;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.mapper.PacienteMapper;
import com.imb2025.smedico.service.IPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {

    @Autowired
    private IPacienteService service;

    // 🔹 Obtener todos los pacientes
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<PacienteResponseDto>>> findAllPacientes() {
        List<Paciente> lista = service.findAll();
        List<PacienteResponseDto> listaResponse = new ArrayList<>();
        PacienteMapper mapper = new PacienteMapper();

        for (Paciente p : lista) {
            PacienteResponseDto dto = mapper.toResponseDto(p);
            listaResponse.add(dto);
        }

        ApiResponseSuccessDto<List<PacienteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                listaResponse.isEmpty() ? "No hay pacientes registrados" : "Lista de pacientes",
                listaResponse
        );

        return ResponseEntity.ok(resp);
    }

    // 🔹 Obtener paciente por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<PacienteResponseDto>> findPacienteById(@PathVariable("id") Long id) {
        Paciente paciente = service.findById(id);
        PacienteMapper mapper = new PacienteMapper();
        PacienteResponseDto dto = mapper.toResponseDto(paciente);

        ApiResponseSuccessDto<PacienteResponseDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Paciente encontrado correctamente",
                dto
        );

        return ResponseEntity.ok(resp);
    }

    // 🔹 Crear paciente
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<PacienteResponseDto>> create(
            @Valid @RequestBody PacienteRequestDto dto) {

        PacienteMapper mapper = new PacienteMapper();
        Paciente entidad = mapper.fromDto(dto);
        Paciente nuevo = service.create(entidad);
        PacienteResponseDto responseDto = mapper.toResponseDto(nuevo);

        ApiResponseSuccessDto<PacienteResponseDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Paciente creado correctamente",
                responseDto
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // 🔹 Actualizar paciente
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<PacienteResponseDto>> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody PacienteRequestDto dto) {

        PacienteMapper mapper = new PacienteMapper();
        Paciente entidad = mapper.fromDto(dto);
        Paciente actualizado = service.update(id, entidad);
        PacienteResponseDto responseDto = mapper.toResponseDto(actualizado);

        ApiResponseSuccessDto<PacienteResponseDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Paciente actualizado correctamente",
                responseDto
        );

        return ResponseEntity.ok(resp);
    }

    // 🔹 Eliminar paciente
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deletePaciente(@PathVariable("id") Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(
                true,
                "Paciente eliminado correctamente",
                "Id: " + id
        );

        return ResponseEntity.ok(resp);
    }

    // 🔹 Buscar pacientes ordenados por apellido
    @GetMapping("/porapellido")
    public ResponseEntity<ApiResponseSuccessDto<List<PacienteResponseDto>>> getPacientesOrdenados() {
        List<Paciente> lista = service.findAllOrder();
        PacienteMapper mapper = new PacienteMapper();
        List<PacienteResponseDto> listaResponse = new ArrayList<>();

        for (Paciente p : lista) {
            listaResponse.add(mapper.toResponseDto(p));
        }

        ApiResponseSuccessDto<List<PacienteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                listaResponse.isEmpty() ? "No hay pacientes registrados" : "Lista de pacientes ordenados por apellido",
                listaResponse
        );

        return ResponseEntity.ok(resp);
    }

    // 🔹 Buscar por DNI
    @GetMapping("/dni/{numeroDni}")
    public ResponseEntity<ApiResponseSuccessDto<List<PacienteResponseDto>>> getPacienteByDni(@PathVariable String numeroDni) {
        List<Paciente> lista = service.findByDni(numeroDni);
        PacienteMapper mapper = new PacienteMapper();
        List<PacienteResponseDto> listaResponse = new ArrayList<>();

        for (Paciente p : lista) {
            listaResponse.add(mapper.toResponseDto(p));
        }

        ApiResponseSuccessDto<List<PacienteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                listaResponse.isEmpty() ? "No se encontraron pacientes con ese DNI" : "Pacientes encontrados",
                listaResponse
        );

        return ResponseEntity.ok(resp);
    }

    // 🔹 Buscar por dominio de email
    @GetMapping("/domain/{domainValue}")
    public ResponseEntity<ApiResponseSuccessDto<List<PacienteResponseDto>>> getPacienteByDomainEmail(@PathVariable String domainValue) {
        List<Paciente> lista = service.findByDomainEmail(domainValue);
        PacienteMapper mapper = new PacienteMapper();
        List<PacienteResponseDto> listaResponse = new ArrayList<>();

        for (Paciente p : lista) {
            listaResponse.add(mapper.toResponseDto(p));
        }

        ApiResponseSuccessDto<List<PacienteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                listaResponse.isEmpty() ? "No se encontraron pacientes con ese dominio" : "Pacientes encontrados",
                listaResponse
        );

        return ResponseEntity.ok(resp);
    }

    // 🔹 Contar pacientes
    @GetMapping("/cantidad")
    public ResponseEntity<ApiResponseSuccessDto<Long>> getCountPacientes() {
        long cantidad = service.countBy();

        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(
                true,
                cantidad == 0 ? "No hay pacientes registrados" : "Cantidad total de pacientes",
                cantidad
        );

        return ResponseEntity.ok(resp);
    }
    @GetMapping("/activos")
    public ResponseEntity<ApiResponseSuccessDto<List<PacienteResponseDto>>> getPacientesActivos() {
        List<Paciente> lista = service.findActivos();
        PacienteMapper mapper = new PacienteMapper();
        List<PacienteResponseDto> listaResponse = new ArrayList<>();
        for (Paciente p : lista) {
            listaResponse.add(mapper.toResponseDto(p));
        }

        ApiResponseSuccessDto<List<PacienteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                listaResponse.isEmpty() ? "No hay pacientes activos" : "Lista de pacientes activos",
                listaResponse
        );

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/inactivos")
    public ResponseEntity<ApiResponseSuccessDto<List<PacienteResponseDto>>> getPacientesInactivos() {
        List<Paciente> lista = service.findInactivos();
        PacienteMapper mapper = new PacienteMapper();
        List<PacienteResponseDto> listaResponse = new ArrayList<>();
        for (Paciente p : lista) {
            listaResponse.add(mapper.toResponseDto(p));
        }

        ApiResponseSuccessDto<List<PacienteResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                listaResponse.isEmpty() ? "No hay pacientes inactivos" : "Lista de pacientes inactivos",
                listaResponse
        );

        return ResponseEntity.ok(resp);
    }
}
