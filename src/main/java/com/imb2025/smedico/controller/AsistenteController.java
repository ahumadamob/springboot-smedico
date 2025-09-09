package com.imb2025.smedico.controller;
//Controller

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.AsistenteRequestDto;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.service.IAsistenteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/asistentes")
public class AsistenteController {

    @Autowired
    private IAsistenteService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Asistente>>> findAll() {
        List<Asistente> lista = service.findAll();

        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        ApiResponseSuccessDto<List<Asistente>> resp = new ApiResponseSuccessDto<>(
                true,
                "Listado de asistentes obtenido con éxito",
                lista
        );
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Asistente>> findById(@PathVariable Long id) {
        Asistente asistente = service.findById(id);

        ApiResponseSuccessDto<Asistente> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente encontrado con éxito",
                asistente
        );
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Asistente>> create(@Valid @RequestBody AsistenteRequestDto dto) {
        Asistente asistente = service.fromDto(dto);
        Asistente creado = service.create(asistente);

        ApiResponseSuccessDto<Asistente> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente creado con éxito",
                creado
        );
        return ResponseEntity.status(201).body(resp); // 201 CREATED
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Asistente>> update(
            @PathVariable Long id,
            @Valid @RequestBody AsistenteRequestDto dto) {

        Asistente asistente = service.fromDto(dto);
        Asistente actualizado = service.update(id, asistente);

        ApiResponseSuccessDto<Asistente> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente actualizado con éxito",
                actualizado
        );
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>(
                true,
                "Asistente eliminado con éxito",
                null
        );
        return ResponseEntity.ok(resp);
    }
}
