package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.EstudioResponseDto;
import com.imb2025.smedico.dto.request.EstudioRequestDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.mapper.EstudioMapper;
import com.imb2025.smedico.service.IEstudioService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estudio")
public class EstudioController {

    @Autowired
    private IEstudioService service;

    @Autowired
    private EstudioMapper mapper;

    @GetMapping
    public ResponseEntity<List<EstudioResponseDto>> findAll() {
        List<Estudio> lista = service.findAll();
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.ok(java.util.Collections.emptyList()); // <- evita choque con noContent()
        }
        List<EstudioResponseDto> dtoList = lista.stream()
                .map(EstudioResponseDto::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtoList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstudioResponseDto> findById(@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Estudio entidad = service.findById(id);
        return ResponseEntity.ok(new EstudioResponseDto(entidad));
    }

    @GetMapping("/ordenados")
    public ResponseEntity<List<EstudioResponseDto>> findAllNombreOrdenados() {
        var lista = service.findAllOrder();
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.ok(java.util.Collections.emptyList());
        }
        var dto = lista.stream().map(EstudioResponseDto::new).toList();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<EstudioResponseDto>> buscar(@RequestParam String nombre) {
        var lista = service.findByNombre(nombre);
        if (lista == null || lista.isEmpty()) {
            return ResponseEntity.ok(java.util.Collections.emptyList());
        }
        var dto = lista.stream().map(EstudioResponseDto::new).toList();
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/count/especialidad/{id}")
    public ResponseEntity<Long> countPorEspecialidad(@PathVariable("id") Long especialidadId) {
        return ResponseEntity.ok(service.countByEspecialidadId(especialidadId));
    }

    @PostMapping
    public ResponseEntity<EstudioResponseDto> create(@Valid @RequestBody EstudioRequestDto dto) {
        Estudio entidad = mapper.fromDto(dto);
        Estudio guardado = service.create(entidad);
        return ResponseEntity.status(201).body(new EstudioResponseDto(guardado));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EstudioResponseDto> update(@PathVariable("id") Long id,
                                                     @Valid @RequestBody EstudioRequestDto dto) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        Estudio entidad = mapper.fromDto(dto);
        entidad.setId(id);
        Estudio actualizado = service.update(id, entidad);
        return ResponseEntity.ok(new EstudioResponseDto(actualizado)); // <-- variable correcta
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (!service.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }
}