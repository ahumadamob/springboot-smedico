package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.EstudioRequestDto;
import com.imb2025.smedico.dto.EstudioResponseDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.service.IEstudioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estudio")
public class EstudioController {

    @Autowired
    private IEstudioService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Estudio> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 sin body
        }
        List<EstudioResponseDto> dtoList = lista.stream()
                .map(EstudioResponseDto::new)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstudioResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                "Listado de estudios (DTO)",
                dtoList
        );
        return ResponseEntity.ok(resp); // 200 con DTOs, sin ciclos
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstudioResponseDto>> findById(@PathVariable("id") Long id) {
        Estudio estudio = service.findById(id);
        EstudioResponseDto dto = new EstudioResponseDto(estudio);

        ApiResponseSuccessDto<EstudioResponseDto> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio (DTO)",
                dto
        );
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/completo")
    public ResponseEntity<ApiResponseSuccessDto<List<EstudioResponseDto>>> findAllDTO() {
        List<EstudioResponseDto> lista = service.findAll().stream()
                .map(EstudioResponseDto::new)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstudioResponseDto>> resp = new ApiResponseSuccessDto<>(
                true,
                "Listado de estudios (DTO) obtenido correctamente",
                lista
        );
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Estudio>> create(
            @Valid @RequestBody EstudioRequestDto dto) throws Exception {
        Estudio creado = service.create(service.fromDto(dto));
        ApiResponseSuccessDto<Estudio> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio creado correctamente",
                creado
        );
        return ResponseEntity.status(201).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Estudio>> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody EstudioRequestDto dto) throws Exception {
        Estudio actualizado = service.update(id, service.fromDto(dto));
        ApiResponseSuccessDto<Estudio> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio actualizado correctamente",
                actualizado
        );
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio " + id + " eliminado correctamente.",
                null
        );
        return ResponseEntity.ok(resp);
    }
}
