package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.dto.mapper.AfiliacionMapper;
import com.imb2025.smedico.dto.request.AfiliacionRequestDto;
import com.imb2025.smedico.dto.response.AfiliacionResponseDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/api/afiliaciones")
public class AfiliacionController {

    private final IAfiliacionService service;

    public AfiliacionController(IAfiliacionService service) {
        this.service = service;
    }

    // === Ejercicio 1: listados por booleano ===
    @GetMapping("/activas")
    public ResponseEntity<ApiResponseSuccessDto<List<AfiliacionResponseDto>>> listarActivas() {
        List<Afiliacion> lista = service.findByActivaTrue();
        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(AfiliacionMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Afiliaciones activas", dtos));
    }

    @GetMapping("/inactivas")
    public ResponseEntity<ApiResponseSuccessDto<List<AfiliacionResponseDto>>> listarInactivas() {
        List<Afiliacion> lista = service.findByActivaFalse();
        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(AfiliacionMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Afiliaciones inactivas", dtos));
    }

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<AfiliacionResponseDto>>> getAllAfiliaciones() {
        List<Afiliacion> lista = service.findAll();
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(AfiliacionMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Listado de afiliaciones", dtos));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> getAfiliacionById(@PathVariable Long id) {
        Afiliacion afiliacion = service.findById(id);
        AfiliacionResponseDto dto = AfiliacionMapper.toResponseDto(afiliacion);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Afiliación encontrada", dto));
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> createAfiliacion(
            @Valid @RequestBody AfiliacionRequestDto dto) {
        Afiliacion creada = service.create(dto);
        AfiliacionResponseDto respDto = AfiliacionMapper.toResponseDto(creada);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new ApiResponseSuccessDto<>(true, "Afiliación creada con éxito", respDto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> updateAfiliacion(
            @PathVariable Long id,
            @Valid @RequestBody AfiliacionRequestDto dto) {
        Afiliacion actualizada = service.update(id, dto);
        AfiliacionResponseDto respDto = AfiliacionMapper.toResponseDto(actualizada);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Afiliación actualizada con éxito", respDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteAfiliacion(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Afiliación eliminada con éxito", null));
    }

    // Extras que ya tenías
    @GetMapping("/search")
    public ResponseEntity<?> searchByMinId(@RequestParam(name = "minId", required = true) Long minId) {
        if (minId == null || minId < 0) {
            ApiResponseSuccessDto<List<AfiliacionResponseDto>> bad =
                    new ApiResponseSuccessDto<>(false, "minId debe ser un número >= 0", null);
            return ResponseEntity.badRequest().body(bad);
        }
        List<Afiliacion> lista = service.findByIdGreaterThan(minId);
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(AfiliacionMapper::toResponseDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Listado filtrado por minId=" + minId, dtos));
    }

    @GetMapping("/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMinId(
            @RequestParam(name = "minId", required = true) Long minId) {
        if (minId == null || minId < 0) {
            ApiResponseSuccessDto<Long> bad =
                    new ApiResponseSuccessDto<>(false, "minId debe ser un número >= 0", null);
            return ResponseEntity.badRequest().body(bad);
        }
        long count = service.countByIdGreaterThan(minId);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Cantidad de afiliaciones con id > " + minId, count));
    }
}
