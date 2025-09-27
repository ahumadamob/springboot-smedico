package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.dto.AfiliacionResponseDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/api/afiliaciones")
public class AfiliacionController {

    @Autowired
    private IAfiliacionService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<AfiliacionResponseDto>>> getAllAfiliaciones() {
        List<Afiliacion> lista = service.findAll();
        List<AfiliacionResponseDto> dtos = lista.stream()
                .map(this::toResponseDto)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<AfiliacionResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, "Listado de afiliaciones", dtos);

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> getAfiliacionById(@PathVariable Long id) {
        Afiliacion afiliacion = service.findById(id);
        AfiliacionResponseDto dto = toResponseDto(afiliacion);

        ApiResponseSuccessDto<AfiliacionResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación encontrada", dto);

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> createAfiliacion(
            @Valid @RequestBody AfiliacionRequestDto dto) {

        Afiliacion afiliacion = service.fromDto(dto);
        Afiliacion creada = service.create(afiliacion);

        AfiliacionResponseDto respDto = toResponseDto(creada);

        ApiResponseSuccessDto<AfiliacionResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación creada con éxito", respDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<AfiliacionResponseDto>> updateAfiliacion(
            @PathVariable Long id,
            @Valid @RequestBody AfiliacionRequestDto dto) {

        Afiliacion afiliacion = service.fromDto(dto);
        Afiliacion actualizada = service.update(id, afiliacion);

        AfiliacionResponseDto respDto = toResponseDto(actualizada);

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

    // 🔹 Mapper interno: Entity -> ResponseDto
    private AfiliacionResponseDto toResponseDto(Afiliacion afiliacion) {
        return new AfiliacionResponseDto(
                afiliacion.getId(),
                afiliacion.getNumeroAfiliado(),
                afiliacion.getFechaVigenciaDesde(),
                afiliacion.getFechaHasta(),
                afiliacion.getPaciente() != null ? afiliacion.getPaciente().getId() : null,
                afiliacion.getObra() != null ? afiliacion.getObra().getId() : null
        );
    }
}
