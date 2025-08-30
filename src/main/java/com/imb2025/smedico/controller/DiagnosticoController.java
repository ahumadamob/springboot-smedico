package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.service.IDiagnosticoService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.DiagnosticoRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    @Autowired
    private IDiagnosticoService service;

    // Lista todos
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Diagnostico>>> getAll() {
        List<Diagnostico> diagnosticos = service.findAll();
        ApiResponseSuccessDto<List<Diagnostico>> resp =
                new ApiResponseSuccessDto<>(true, "Listado de diagnósticos", diagnosticos);
        return ResponseEntity.ok(resp); // 200 OK
    }

    // Busca por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Diagnostico>> getDiagnosticoById(@PathVariable Long id) {
    	Diagnostico data = service.findById(id);

        ApiResponseSuccessDto<Diagnostico> resp =
            new ApiResponseSuccessDto<>(true, "Diagnostico encontrado", data);

        return ResponseEntity.ok(resp);
    }

    // Crea nuevo
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Diagnostico>> crearDiagnostico(@Valid @RequestBody DiagnosticoRequestDto dto) {
        Diagnostico nuevo = service.fromDto(dto);
        Diagnostico guardado = service.create(nuevo);
        ApiResponseSuccessDto<Diagnostico> resp =
                new ApiResponseSuccessDto<>(true, "Diagnóstico creado correctamente", guardado);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp); // 201 CREATED
    }

    // Actualiza el existente
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Diagnostico>> actualizarDiagnostico(
            @PathVariable Long id,@Valid @RequestBody DiagnosticoRequestDto dto) {
        
    	Diagnostico diagnostico = service.fromDto(dto);
        Diagnostico actualizado = service.update(id, diagnostico);
        
        ApiResponseSuccessDto<Diagnostico> resp =
                new ApiResponseSuccessDto<>(true, "Diagnóstico actualizado correctamente", actualizado);
        
        return ResponseEntity.ok(resp); // 200 OK
    }

    // Elimina por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        service.deleteById(id);
        
        ApiResponseSuccessDto<Void> resp =
                new ApiResponseSuccessDto<>(true, "Diagnóstico eliminado correctamente", null);
        
        return ResponseEntity.ok(resp);
    }

    
}
