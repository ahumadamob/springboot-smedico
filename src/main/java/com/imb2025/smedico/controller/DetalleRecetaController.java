package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.DetalleRecetaRequestDto;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.IDetalleRecetaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/detalleReceta")
public class DetalleRecetaController {

    @Autowired
    private IDetalleRecetaService service;

    // Método utilitario para construir respuestas
    private <T> ResponseEntity<ApiResponseSuccessDto<T>> buildResponse(T data, String message, HttpStatus status) {
        ApiResponseSuccessDto<T> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(status.is2xxSuccessful());
        resp.setData(data);
        resp.setMessage(message);
        return ResponseEntity.status(status).body(resp);
    }

    // Listar todas las DetalleRecetas
    @Operation(summary = "Listar todas las DetalleRecetas")
    @ApiResponse(responseCode = "200", description = "Listado de DetalleRecetas obtenido con éxito")
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleReceta>>> findAll() {
        List<DetalleReceta> detalles = service.findAll();
        return buildResponse(detalles, "Listado de DetalleRecetas obtenido con éxito", HttpStatus.OK);
    }

    // Obtener DetalleReceta por ID
    @Operation(summary = "Obtener DetalleReceta por ID")
    @ApiResponse(responseCode = "200", description = "DetalleReceta encontrada con éxito")
    @ApiResponse(responseCode = "404", description = "DetalleReceta no encontrada")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> findById(@PathVariable Long id) {
        DetalleReceta detalle = service.findById(id);
        if (detalle == null) {
            return buildResponse(null, "DetalleReceta no encontrada", HttpStatus.NOT_FOUND);
        }
        return buildResponse(detalle, "DetalleReceta encontrada con éxito", HttpStatus.OK);
    }

    // Crear nueva DetalleReceta
    @Operation(summary = "Crear nueva DetalleReceta")
    @ApiResponse(responseCode = "201", description = "DetalleReceta creada con éxito")
    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos")
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> save(@Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta nueva = service.create(service.convertFromDto(dto));
        return buildResponse(nueva, "DetalleReceta creada con éxito", HttpStatus.CREATED);
    }

    // Actualizar DetalleReceta por ID
    @Operation(summary = "Actualizar DetalleReceta por ID")
    @ApiResponse(responseCode = "200", description = "DetalleReceta actualizada con éxito")
    @ApiResponse(responseCode = "404", description = "DetalleReceta no encontrada")
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleReceta>> update(@PathVariable Long id,
                                                                       @Valid @RequestBody DetalleRecetaRequestDto dto) {
        DetalleReceta existente = service.findById(id);
        if (existente == null) {
            return buildResponse(null, "DetalleReceta no encontrada", HttpStatus.NOT_FOUND);
        }
        DetalleReceta actualizada = service.update(id, service.convertFromDto(dto));
        return buildResponse(actualizada, "DetalleReceta actualizada con éxito", HttpStatus.OK);
    }

    // Eliminar DetalleReceta por ID
    @Operation(summary = "Eliminar DetalleReceta por ID")
    @ApiResponse(responseCode = "200", description = "DetalleReceta eliminada con éxito")
    @ApiResponse(responseCode = "404", description = "DetalleReceta no encontrada")
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteById(@PathVariable Long id) {
        DetalleReceta existente = service.findById(id);
        if (existente == null) {
            return buildResponse(null, "DetalleReceta no encontrada", HttpStatus.NOT_FOUND);
        }
        service.deleteById(id);
        return buildResponse(null, "DetalleReceta eliminada con éxito", HttpStatus.OK);
    }
}
