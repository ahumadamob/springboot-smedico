package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.EstudioRequestDTO;
import com.imb2025.smedico.dto.EstudioResponseDTO;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.service.IEstudioService;

@RestController
@RequestMapping("/estudio")
public class EstudioControler {

    @Autowired
    private IEstudioService service;

    @GetMapping
    public ResponseEntity<List<Estudio>> findAll() {
        List<Estudio> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estudio> findById(@PathVariable("id") Long id) {
        Estudio estudio = service.findById(id);
        if (estudio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudio);
    }

    @GetMapping("/completo")
    public ResponseEntity<List<EstudioResponseDTO>> findAllDTO() {
        List<EstudioResponseDTO> lista = service.findAll().stream()
                .map(EstudioResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping
    public ResponseEntity<Estudio> create(@RequestBody EstudioRequestDTO dto) throws Exception {
        Estudio estudio = service.fromDto(dto);
        Estudio creado = service.create(estudio);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Estudio> update(@PathVariable("id") Long id, @RequestBody EstudioRequestDTO dto) throws Exception {
        Estudio estudio = service.fromDto(dto);
        Estudio actualizado = service.update(estudio, id);
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Estudio " + id + " eliminado correctamente.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
}
