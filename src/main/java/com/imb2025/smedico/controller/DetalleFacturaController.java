package com.imb2025.smedico.controller;

import org.springframework.web.bind.annotation.RestController;
import com.imb2025.smedico.service.IDetalleFacturaService;
import com.imb2025.smedico.entity.DetalleFactura;
import com.imb2025.smedico.dto.DetalleFacturaRequestDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import jakarta.validation.Valid;

@RestController
public class DetalleFacturaController {

    private final IDetalleFacturaService detalleFacturaService;
    public DetalleFacturaController(IDetalleFacturaService detalleFacturaService) {
        this.detalleFacturaService = detalleFacturaService;
    }

    @GetMapping("/detallefactura")
    public ResponseEntity<ApiResponseSuccessDto<List<DetalleFactura>>> findAll() {
        List<DetalleFactura> lista = detalleFacturaService.findAll();
        ApiResponseSuccessDto<List<DetalleFactura>> resp = new ApiResponseSuccessDto<>(true, "Listado de detalles de factura", lista);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/detallefactura/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFactura>> findById(@PathVariable Long id) {
        DetalleFactura detalle = detalleFacturaService.findById(id);
        ApiResponseSuccessDto<DetalleFactura> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura encontrada", detalle);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/detallefactura")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFactura>> create(
        @Valid @RequestBody DetalleFacturaRequestDto dto) throws Exception {
        DetalleFactura detalle = detalleFacturaService.fromDto(dto);
        DetalleFactura creado = detalleFacturaService.create(detalle);
        ApiResponseSuccessDto<DetalleFactura> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura creada", creado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/detallefactura/{id}")
    public ResponseEntity<ApiResponseSuccessDto<DetalleFactura>> update(
        @PathVariable Long id,
        @Valid @RequestBody DetalleFacturaRequestDto dto) throws Exception {
        DetalleFactura entidad = detalleFacturaService.fromDto(dto);
        entidad.setId(id);
        DetalleFactura saved = detalleFacturaService.update(id, entidad);
        ApiResponseSuccessDto<DetalleFactura> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura actualizada", saved);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/detallefactura/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        detalleFacturaService.deleteById(id);
        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>(true, "DetalleFactura eliminada", null);
        return ResponseEntity.ok(resp);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
