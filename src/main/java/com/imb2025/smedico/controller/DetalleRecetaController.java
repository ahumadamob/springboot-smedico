/*package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.DetalleRecetaRequestDto;
import com.imb2025.smedico.dto.response.DetalleRecetaResponseDto;
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
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleRecetaResponseDto>>> findAll() {
        List<DetalleRecetaResponseDto> detalles = detalleRecetaService.findAll();
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Listado de Recetas", detalles));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleRecetaResponseDto>> findById(@PathVariable Long id) {
        DetalleRecetaResponseDto detalle = detalleRecetaService.findById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Receta encontrada", detalle));
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleRecetaResponseDto>> create(
            @Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleRecetaResponseDto created = detalleRecetaService.create(dto);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Receta creada", created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleRecetaResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleRecetaResponseDto updated = detalleRecetaService.update(id, dto);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Receta actualizada", updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable Long id) {
        detalleRecetaService.deleteById(id);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "DetalleReceta eliminado correctamente", null));
    }
}
*/
