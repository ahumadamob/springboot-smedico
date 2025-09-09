package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.service.IConsultaService;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @Operation(summary = "Listar consultas")
    @ApiResponse(responseCode = "200", description = "Listado de consultas")
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Consulta>>> findAll() {
        List<Consulta> lista = consultaService.findAll();
        ApiResponseSuccessDto<List<Consulta>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(lista);
        resp.setMessage(lista.isEmpty() ? "No hay consultas" : "Listado de consultas");
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Obtener consulta por id")
    @ApiResponse(responseCode = "200", description = "Consulta encontrada")
    @ApiResponse(responseCode = "404", description = "Consulta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Consulta>> getById(@PathVariable Long id) {
        Consulta c = consultaService.findById(id);
        ApiResponseSuccessDto<Consulta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(c);
        resp.setMessage("Consulta encontrada");
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Crear consulta")
    @ApiResponse(responseCode = "201", description = "Consulta creada")
    @ApiResponse(responseCode = "400", description = "Error de validación de datos")
    @ApiResponse(responseCode = "404", description = "Turno no encontrado")
    @ApiResponse(responseCode = "422", description = "Regla de negocio: turno ya asignado")
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Consulta>> create(@Valid @RequestBody ConsultaRequestDto dto) {
        Consulta creada = consultaService.createFromDto(dto);
        ApiResponseSuccessDto<Consulta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(creada);
        resp.setMessage("Consulta creada");
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @Operation(summary = "Actualizar consulta")
    @ApiResponse(responseCode = "200", description = "Consulta actualizada")
    @ApiResponse(responseCode = "400", description = "Error de validación de datos")
    @ApiResponse(responseCode = "404", description = "Consulta o Turno no encontrado")
    @ApiResponse(responseCode = "422", description = "Regla de negocio: turno ya asignado")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Consulta>> update(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaRequestDto dto) {

        Consulta actualizada = consultaService.updateFromDto(id, dto);
        ApiResponseSuccessDto<Consulta> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(actualizada);
        resp.setMessage("Consulta actualizada");
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Eliminar consulta")
    @ApiResponse(responseCode = "204", description = "Consulta eliminada")
    @ApiResponse(responseCode = "404", description = "Consulta no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 sin body
    }
}
