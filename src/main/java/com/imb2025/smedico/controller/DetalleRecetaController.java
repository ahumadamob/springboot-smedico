package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseDTO;
import com.imb2025.smedico.dto.DetalleRecetaRequestDTO;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.service.IDetalleRecetaService;
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

    @GetMapping
    public ResponseEntity<ApiResponseDTO<List<DetalleReceta>>> findAll() {
        List<DetalleReceta> lista = service.findAll();

        ApiResponseDTO<List<DetalleReceta>> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(lista);
        resp.setMessage("Lista de DetalleRecetas obtenida correctamente");

        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<DetalleReceta>> findById(@PathVariable Long id) {
        DetalleReceta detalle = service.findById(id);

        ApiResponseDTO<DetalleReceta> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(detalle);
        resp.setMessage("DetalleReceta encontrada correctamente");

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseDTO<DetalleReceta>> save(@RequestBody DetalleRecetaRequestDTO dto) {
        DetalleReceta nueva = service.saveFromDTO(dto);

        ApiResponseDTO<DetalleReceta> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(nueva);
        resp.setMessage("DetalleReceta creada correctamente");

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<DetalleReceta>> update(@PathVariable Long id,
                                                                @RequestBody DetalleRecetaRequestDTO dto) {
        DetalleReceta actualizada = service.updateFromDTO(id, dto);

        ApiResponseDTO<DetalleReceta> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(actualizada);
        resp.setMessage("DetalleReceta actualizada correctamente");

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseDTO<Void>> deleteById(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseDTO<Void> resp = new ApiResponseDTO<>();
        resp.setSuccess(true);
        resp.setData(null);
        resp.setMessage("DetalleReceta eliminada correctamente");

        return ResponseEntity.ok(resp);
    }
}
