package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.request.DetalleRecetaRequestDto;
import com.imb2025.smedico.dto.response.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.IDetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/detalle-recetas")
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService detalleRecetaService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleReceta>>> findAll() {
        List<DetalleReceta> detalleRecetas = detalleRecetaService.findAll();
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(detalleRecetas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> findById(@PathVariable Long id) {
        DetalleReceta detalleReceta = detalleRecetaService.findById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(detalleReceta));
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> create(
            @Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta created = detalleRecetaService.create(dto);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> update(
            @PathVariable Long id,
            @Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta updated = detalleRecetaService.update(id, dto);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable Long id) {
        detalleRecetaService.deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>("DetalleReceta eliminado correctamente"));
    }
}


