package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.DetalleFacturaRequestDto;
import com.imb2025.smedico.dto.response.DetalleFacturaResponseDto;
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
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleFacturaResponseDto>>> findAll() {
        List<DetalleFacturaResponseDto> lista = detalleFacturaService.findAll();
        return buildResponse("Listado de detalles de factura", lista, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un detalle de factura por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "DetalleFactura encontrada"),
        @ApiResponse(responseCode = "404", description = "DetalleFactura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFacturaResponseDto>> findById(@PathVariable Long id) {
        DetalleFacturaResponseDto detalle = detalleFacturaService.findById(id);
        ApiResponseSuccessDto<DetalleFacturaResponseDto> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura encontrada", detalle);
        return ResponseEntity.ok(resp);
    }

    @Operation(summary = "Crea un nuevo detalle de factura")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "DetalleFactura creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Datos inválidos o factura inexistente")
    })
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<DetalleFacturaResponseDto>> create(
            @Valid @RequestBody DetalleFacturaRequestDto dto) {
        DetalleFacturaResponseDto creado = detalleFacturaService.create(dto);
        ApiResponseSuccessDto<DetalleFacturaResponseDto> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura creada", creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @Operation(summary = "Actualiza un detalle de factura existente por ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "DetalleFactura actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "DetalleFactura no encontrada")
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFacturaResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody DetalleFacturaRequestDto dto) {
        DetalleFacturaResponseDto actualizado = detalleFacturaService.update(id, dto);
        ApiResponseSuccessDto<DetalleFacturaResponseDto> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura actualizada", actualizado);
        return ResponseEntity.ok(resp);
    }
    @Operation(summary = "Lista los detalles de factura vigentes (fechaVigencia >= hoy)")
@GetMapping("/vigentes")
public ResponseEntity<ApiResponseSuccessDto<List<DetalleFacturaResponseDto>>> getVigentes() {
    List<DetalleFacturaResponseDto> lista = detalleFacturaService.findVigentes();
    return buildResponse("Listado de detalles vigentes", lista, HttpStatus.OK);
}

@Operation(summary = "Lista los detalles de factura vencidos (fechaVigencia < hoy)")
@GetMapping("/vencidos")
public ResponseEntity<ApiResponseSuccessDto<List<DetalleFacturaResponseDto>>> getVencidos() {
    List<DetalleFacturaResponseDto> lista = detalleFacturaService.findVencidos();
    return buildResponse("Listado de detalles vencidos", lista, HttpStatus.OK);
}

}
