package com.imb2025.smedico.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.service.IEncuestaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {

    @Autowired
    private IEncuestaService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Map<String, Object>>>> getAllEncuesta() {
        List<Encuesta> list = service.findAll();
        List<Map<String, Object>> data = list.stream().map(this::toMap).toList();
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "OK", data));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Map<String, Object>>> getEncuestaById(@PathVariable Long id) {
        Encuesta e = service.findById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuesta encontrada", toMap(e)));
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Map<String, Object>>> createEncuesta(
            @Valid @RequestBody EncuestaRequestDto dto) throws Exception {
        Encuesta creada = service.create(service.fromDto(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseSuccessDto<>(true, "Encuesta creada", toMap(creada)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Map<String, Object>>> update(
            @PathVariable Long id, @Valid @RequestBody EncuestaRequestDto dto) throws Exception {
        Encuesta actualizada = service.update(id, service.fromDto(dto));
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuesta actualizada", toMap(actualizada)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Encuesta eliminada", null));
    }

    // --- Mapeo sin crear clases nuevas ---
    private Map<String, Object> toMap(Encuesta e) {
        Map<String, Object> r = new HashMap<>();
        r.put("id", e.getId());
        r.put("puntaje", e.getPuntaje());
        r.put("comentario", e.getComentario());
        r.put("pacienteId", (e.getPaciente() != null) ? e.getPaciente().getId() : null);
        r.put("consultaId", (e.getConsulta() != null) ? e.getConsulta().getId() : null);
        return r;
    }
}

