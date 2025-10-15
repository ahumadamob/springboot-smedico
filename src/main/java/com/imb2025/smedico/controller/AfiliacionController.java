package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.dto.mapper.AfiliacionMapper;
import com.imb2025.smedico.dto.request.AfiliacionRequestDto;    // <-- paquete request correcto
import com.imb2025.smedico.dto.response.AfiliacionResponseDto; // <-- paquete response correcto
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

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<AfiliacionResponseDto>>> getAllAfiliaciones() {
        List<Afiliacion> lista = service.findAll();
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 si está vacío
        }

        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(AfiliacionMapper::toResponseDto)   // <-- usa el Mapper (incluye version)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<AfiliacionResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, "Listado de afiliaciones", dtos);

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> getAfiliacionById(@PathVariable Long id) {
        Afiliacion afiliacion = service.findById(id);
        AfiliacionResponseDto dto = AfiliacionMapper.toResponseDto(afiliacion); // <-- usa Mapper

        ApiResponseSuccessDto<AfiliacionResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación encontrada", dto);

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> createAfiliacion(
            @Valid @RequestBody AfiliacionRequestDto dto) {

        // Ahora el service recibe el DTO (regla 4.1 ya cumplida en el service)
        Afiliacion creada = service.create(dto);

        AfiliacionResponseDto respDto = AfiliacionMapper.toResponseDto(creada); // incluye version

        ApiResponseSuccessDto<AfiliacionResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación creada con éxito", respDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> updateAfiliacion(
            @PathVariable Long id,
            @Valid @RequestBody AfiliacionRequestDto dto) {

        // Ahora el service recibe el DTO
        Afiliacion actualizada = service.update(id, dto);

        AfiliacionResponseDto respDto = AfiliacionMapper.toResponseDto(actualizada); // incluye version

        ApiResponseSuccessDto<AfiliacionResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación actualizada con éxito", respDto);

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteAfiliacion(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<Void> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación eliminada con éxito", null);
        return ResponseEntity.ok(resp);
    }

    /**
     * Buscar afiliaciones con id > minId
     * Ej: GET /api/afiliaciones/search?minId=100
     */
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
                .map(AfiliacionMapper::toResponseDto)   // <-- usa Mapper
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<AfiliacionResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, "Listado filtrado por minId=" + minId, dtos);
        return ResponseEntity.ok(resp);
    }

    /**
     * Contar afiliaciones con id > minId
     * Ej: GET /api/afiliaciones/count?minId=100
     */
    @GetMapping("/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMinId(@RequestParam(name = "minId", required = true) Long minId) {
        if (minId == null || minId < 0) {
            ApiResponseSuccessDto<Long> bad =
                    new ApiResponseSuccessDto<>(false, "minId debe ser un número >= 0", null);
            return ResponseEntity.badRequest().body(bad);
        }

        long count = service.countByIdGreaterThan(minId);
        ApiResponseSuccessDto<Long> resp =
                new ApiResponseSuccessDto<>(true, "Cantidad de afiliaciones con id > " + minId, count);
        return ResponseEntity.ok(resp);
    }
}
