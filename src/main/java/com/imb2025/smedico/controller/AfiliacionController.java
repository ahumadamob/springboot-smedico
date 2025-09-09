package com.imb2025.smedico.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/api/afiliaciones")
public class AfiliacionController {

    @Autowired
    private IAfiliacionService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Afiliacion>>> getAllAfiliaciones() {
        List<Afiliacion> lista = service.findAll();
        ApiResponseSuccessDto<List<Afiliacion>> resp =
                new ApiResponseSuccessDto<>(true, "Listado de afiliaciones", lista);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Afiliacion>> getAfiliacionById(@PathVariable Long id) {
        Afiliacion data = service.findById(id);
        ApiResponseSuccessDto<Afiliacion> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación encontrada", data);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Afiliacion>> createAfiliacion(
            @Valid @RequestBody AfiliacionRequestDto dto) throws Exception {

        Afiliacion afiliacion = service.fromDto(dto);
        Afiliacion creada = service.create(afiliacion);

        ApiResponseSuccessDto<Afiliacion> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación creada con éxito", creada);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Afiliacion>> updateAfiliacion(
            @PathVariable Long id,
            @Valid @RequestBody AfiliacionRequestDto dto) throws Exception {

        Afiliacion afiliacion = service.fromDto(dto);
        Afiliacion actualizada = service.update(id, afiliacion);

        ApiResponseSuccessDto<Afiliacion> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación actualizada con éxito", actualizada);

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteAfiliacion(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<Void> resp =
                new ApiResponseSuccessDto<>(true, "Afiliación eliminada con éxito", null);
        return ResponseEntity.ok(resp);
    }
}
