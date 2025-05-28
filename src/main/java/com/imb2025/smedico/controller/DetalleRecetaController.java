package com.imb2025.smedico.controller;

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
    public ResponseEntity<List<DetalleReceta>> findAll() {
        List<DetalleReceta> lista = service.findAll();
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        DetalleReceta detalle = service.findById(id);
        if (detalle == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("DetalleReceta no encontrado con ID: " + id);
        }
        return ResponseEntity.ok(detalle);
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody DetalleRecetaRequestDTO dto) {
        try {
            DetalleReceta nueva = service.saveFromDTO(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(nueva);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al guardar DetalleReceta: " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody DetalleRecetaRequestDTO dto) {
        try {
            DetalleReceta actualizada = service.updateFromDTO(id, dto);
            return ResponseEntity.ok(actualizada);
        } catch (IllegalArgumentException e) {
            // Se usa NOT_FOUND si el detalle o datos relacionados no existen
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Error al actualizar DetalleReceta: " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        DetalleReceta existente = service.findById(id);
        if (existente == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("DetalleReceta no encontrado con ID: " + id);
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build();  // 204 No Content para borrar OK
    }
}
