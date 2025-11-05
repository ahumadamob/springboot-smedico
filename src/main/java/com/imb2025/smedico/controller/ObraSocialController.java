package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;
import com.imb2025.smedico.service.IObraSocialService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * Controlador REST para la gestión de ObraSocial.
 */
@RestController
@RequestMapping("/obrasocial")
@CrossOrigin(origins = "*")
public class ObraSocialController {

    @Autowired
    private IObraSocialService service;

    // 🔹 GET - Listar todas
    @GetMapping
    public ResponseEntity<List<ObraSocialResponseDto>> findAll() {
        List<ObraSocialResponseDto> lista = service.findAll();
        return ResponseEntity.ok(lista);
    }

    // 🔹 GET - Buscar por ID
    @GetMapping("/{id}")
    public ResponseEntity<ObraSocialResponseDto> findById(@PathVariable Long id) {
        ObraSocialResponseDto dto = service.findById(id);
        return ResponseEntity.ok(dto);
    }

    // 🔹 GET - Buscar por nombre
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ObraSocialResponseDto> findByNombre(@PathVariable String nombre) {
        ObraSocialResponseDto dto = service.findByNombre(nombre);
        return ResponseEntity.ok(dto);
    }

    // 🔹 POST - Crear nueva ObraSocial
    @PostMapping
    public ResponseEntity<?> create(@RequestBody ObraSocialRequestDto dto) {
        try {
            ObraSocialResponseDto creada = service.create(dto);
            return ResponseEntity.status(HttpStatus.CREATED).body(creada);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{ \"errors\": [\"" + e.getMessage() + "\"] }");
        }
    }

    // 🔹 PUT - Actualizar
    @PutMapping("/{id}")
    public ResponseEntity<ObraSocialResponseDto> update(@PathVariable Long id, @RequestBody ObraSocialRequestDto dto) {
        ObraSocialResponseDto actualizada = service.update(id, dto);
        return ResponseEntity.ok(actualizada);
    }

    // 🔹 DELETE - Eliminar
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // 🔹 GET - Listar vigentes (fechaVigencia >= hoy)
    @GetMapping("/vigentes")
    public ResponseEntity<List<ObraSocialResponseDto>> findVigentes() {
        List<ObraSocialResponseDto> lista = service.findVigentes(LocalDate.now());
        return ResponseEntity.ok(lista);
    }

    // 🔹 GET - Listar vencidos (fechaVigencia < hoy)
    @GetMapping("/vencidos")
    public ResponseEntity<List<ObraSocialResponseDto>> findVencidos() {
        List<ObraSocialResponseDto> lista = service.findVencidos(LocalDate.now());
        return ResponseEntity.ok(lista);
    }
}
