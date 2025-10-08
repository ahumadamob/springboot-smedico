package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
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
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Listado de Recetas", detalleRecetas));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> findById(@PathVariable Long id) {
        DetalleReceta detalleReceta = detalleRecetaService.findById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<DetalleReceta>(true, "Receta encontrada", detalleReceta));
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> create(
            @Valid @RequestBody DetalleRecetaRequestDto dto) {
    	DetalleReceta detalleReceta = detalleRecetaService.fromDto(dto);
        DetalleReceta created = detalleRecetaService.create(detalleReceta);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Receta creada", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> update(
            @PathVariable Long id,
            @Valid @RequestBody DetalleRecetaRequestDto dto) {
    	DetalleReceta detalleReceta = detalleRecetaService.fromDto(dto);    	        
    	DetalleReceta updated = detalleRecetaService.update(id, detalleReceta);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Receta actualizada", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable Long id) {
        detalleRecetaService.deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "DetalleReceta eliminado correctamente", null));
    }
}


