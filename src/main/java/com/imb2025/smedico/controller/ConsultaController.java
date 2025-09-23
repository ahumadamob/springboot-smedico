package com.imb2025.smedico.controller;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.dto.ConsultaResponseDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.service.IConsultaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import java.util.stream.Collectors;

import java.util.List;


@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<ConsultaResponseDto>>> findAll() {
        List<Consulta> lista = consultaService.findAll();
        List<ConsultaResponseDto> data = lista.stream()
                .map(this::toResponse)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<ConsultaResponseDto>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage(data.isEmpty() ? "No hay consultas" : "Listado de consultas");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultaResponseDto>> getById(@Valid@PathVariable Long id) {
        Consulta c = consultaService.findById(id);
        ConsultaResponseDto data = toResponse(c);

        ApiResponseSuccessDto<ConsultaResponseDto> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage("Consulta encontrada");
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<ConsultaResponseDto>> create(@Valid @RequestBody ConsultaRequestDto dto) {
        Consulta creada = consultaService.createFromDto(dto);
        ConsultaResponseDto data = toResponse(creada);

        ApiResponseSuccessDto<ConsultaResponseDto> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage("Consulta creada");
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultaResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaRequestDto dto) {

        Consulta actualizada = consultaService.updateFromDto(id, dto);
        ConsultaResponseDto data = toResponse(actualizada);

        ApiResponseSuccessDto<ConsultaResponseDto> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage("Consulta actualizada");
        return ResponseEntity.ok(resp);
    }


    @Operation(summary = "Eliminar consulta")
    @ApiResponse(responseCode = "200", description = "Consulta eliminada")
    @ApiResponse(responseCode = "404", description = "Consulta no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@Valid@PathVariable Long id) { 
        consultaService.deleteById(id);
        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(null);
        resp.setMessage("Consulta eliminada");
        return ResponseEntity.ok(resp); // 200 con wrapper (política del curso)
    }
    private ConsultaResponseDto toResponse(Consulta c) {
        if (c == null) return null;
        ConsultaResponseDto dto = new ConsultaResponseDto();
        dto.setId(c.getId());
        dto.setFecha(c.getFecha());
        dto.setDuracionMin(c.getDuracionMin());
        dto.setComentarios(c.getComentarios());
        // Esto NO fuerza cargar la entidad; el proxy de Hibernate expone el id igual:
        dto.setTurnoId(c.getTurno() != null ? c.getTurno().getId() : null);
        return dto;
    }
}

