package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.DetalleFacturaRequestDto;
import com.imb2025.smedico.dto.response.DetalleFacturaResponseDto;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.entity.Factura;
import com.imb2025.smedico.mapper.DetalleFacturaMapper;
import com.imb2025.smedico.repository.FacturaRepository;
import com.imb2025.smedico.service.IDetalleFacturaService;
import com.imb2025.smedico.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/detallefacturas")
public class DetalleFacturaController {

    private final IDetalleFacturaService detalleFacturaService;
    private final FacturaRepository facturaRepository;

    public DetalleFacturaController(IDetalleFacturaService detalleFacturaService, FacturaRepository facturaRepository) {
        this.detalleFacturaService = detalleFacturaService;
        this.facturaRepository = facturaRepository;
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
        List<DetalleFactura> entidades = detalleFacturaService.findAll();
        List<DetalleFacturaResponseDto> lista = entidades.stream()
                .map(DetalleFacturaMapper::toResponseDto)
                .collect(Collectors.toList());
        return buildResponse("Listado de detalles de factura", lista, HttpStatus.OK);
    }

    @Operation(summary = "Obtiene un detalle de factura por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "DetalleFactura encontrada"),
        @ApiResponse(responseCode = "404", description = "DetalleFactura no encontrada")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFacturaResponseDto>> findById(@PathVariable Long id) {
        DetalleFactura entidad = detalleFacturaService.findById(id);
        DetalleFacturaResponseDto detalle = DetalleFacturaMapper.toResponseDto(entidad);
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
    // Mapear DTO -> Entidad
    Factura factura = facturaRepository.findById(dto.getFacturaId())
        .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + dto.getFacturaId()));
    DetalleFactura entidad = DetalleFacturaMapper.fromDto(dto, factura);
    DetalleFactura creadoEntidad = detalleFacturaService.save(entidad);
    DetalleFacturaResponseDto creado = DetalleFacturaMapper.toResponseDto(creadoEntidad);
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
        Factura factura = facturaRepository.findById(dto.getFacturaId())
                .orElseThrow(() -> new ResourceNotFoundException("Factura no encontrada con id " + dto.getFacturaId()));
        DetalleFactura entidad = DetalleFacturaMapper.fromDto(dto, factura);
        entidad.setId(id);
        DetalleFactura actualizadoEntidad = detalleFacturaService.save(entidad);
        DetalleFacturaResponseDto actualizado = DetalleFacturaMapper.toResponseDto(actualizadoEntidad);
        ApiResponseSuccessDto<DetalleFacturaResponseDto> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura actualizada", actualizado);
        return ResponseEntity.ok(resp);
    }
}
