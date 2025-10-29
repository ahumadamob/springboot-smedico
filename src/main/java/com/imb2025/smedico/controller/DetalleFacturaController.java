package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.service.IDetalleFacturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/detallefacturas")
public class DetalleFacturaController {

    private final IDetalleFacturaService detalleFacturaService;

    public DetalleFacturaController(IDetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    // Método auxiliar para crear respuestas estándar
    private <T> ResponseEntity<ApiResponseSuccessDto<T>> buildResponse(String message, T data, HttpStatus status) {
        ApiResponseSuccessDto<T> resp = new ApiResponseSuccessDto<>(true, message, data);
        return ResponseEntity.status(status).body(resp);
    }

    // --- Endpoints ---

    @Operation(summary = "Obtiene todos los detalles de factura")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Listado obtenido correctamente")
    })
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleFactura>>> findAll() {
        List<DetalleFactura> lista = detalleFacturaService.findAll();
        return buildResponse("Listado de detalles de factura", lista, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un detalle de factura por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "DetalleFactura encontrada"),
        @ApiResponse(responseCode = "404", description = "DetalleFactura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFactura>> findById(@PathVariable Long id) {
        DetalleFactura detalle = detalleFacturaService.findById(id);
        return buildResponse("DetalleFactura encontrada", detalle, HttpStatus.OK);
    }

    @Operation(summary = "Crea un nuevo detalle de factura")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "DetalleFactura creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o factura inexistente")
    })
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleFactura>> create(
            @Valid @RequestBody DetalleFacturaRequestDto dto) throws ResourceNotFoundException {

        DetalleFactura detalle = detalleFacturaService.fromDto(dto);
        DetalleFactura creado = detalleFacturaService.create(detalle);
        return buildResponse("DetalleFactura creada correctamente", creado, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualiza un detalle de factura existente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "DetalleFactura actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "DetalleFactura no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFactura>> update(
            @PathVariable Long id,
            @Valid @RequestBody DetalleFacturaRequestDto dto) throws ResourceNotFoundException {

        DetalleFactura entidad = detalleFacturaService.fromDto(dto);
        entidad.setId(id);
        DetalleFactura actualizado = detalleFacturaService.update(id, entidad);
        return buildResponse("DetalleFactura actualizada correctamente", actualizado, HttpStatus.OK);
    }

    @Operation(summary = "Elimina un detalle de factura por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "DetalleFactura eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "DetalleFactura no encontrada")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        detalleFacturaService.deleteById(id);
        return buildResponse("DetalleFactura eliminada correctamente", null, HttpStatus.OK);
    }

    @Operation(summary = "Busca detalles de factura por descripción")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Búsqueda realizada correctamente"),
        @ApiResponse(responseCode = "400", description = "Parámetro de búsqueda inválido")
    })
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleFactura>>> buscarPorDescripcion(
            @RequestParam String descripcion) {
        List<DetalleFactura> lista = detalleFacturaService.findByDescripcion(descripcion);
        return buildResponse("Resultados de búsqueda", lista, HttpStatus.OK);
    }

    @Operation(summary = "Cuenta la cantidad de detalles para una factura")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cantidad obtenida correctamente"),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    @GetMapping("/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> contarPorFactura(
            @RequestParam Long facturaId) {
        long count = detalleFacturaService.countByFacturaId(facturaId);
        return buildResponse("Cantidad de detalles para la factura", count, HttpStatus.OK);
    }
}
