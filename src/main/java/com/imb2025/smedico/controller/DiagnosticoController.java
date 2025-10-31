package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.DiagnosticoRequestDto;
import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.service.IDiagnosticoService;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Validated
@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/diagnosticos")
public class DiagnosticoController {

    private final IDiagnosticoService service;

    public DiagnosticoController(IDiagnosticoService service) {
        this.service = service;
    }

    // GET /diagnosticos
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Diagnostico>>> getAll() {
        List<Diagnostico> data = service.findAll();

        if (data.isEmpty()) {
            // Si tu profe quiere 204 para lista vacía, descomenta esta línea:
            // return ResponseEntity.noContent().build();
        }

        ApiResponseSuccessDto<List<Diagnostico>> resp =
                new ApiResponseSuccessDto<>(true, "Listado de diagnósticos", data);
        return ResponseEntity.ok(resp);
    }

    // GET /diagnosticos/{id}
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Diagnostico>> getById(@PathVariable Long id) {
        Diagnostico data = service.findById(id);
        ApiResponseSuccessDto<Diagnostico> resp =
                new ApiResponseSuccessDto<>(true, "Diagnóstico encontrado", data);
        return ResponseEntity.ok(resp);
    }

    // POST /diagnosticos
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Diagnostico>> create(@Valid @RequestBody DiagnosticoRequestDto dto) {
        Diagnostico nuevo = service.fromDto(dto);
        Diagnostico guardado = service.create(nuevo);

        ApiResponseSuccessDto<Diagnostico> resp =
                new ApiResponseSuccessDto<>(true, "Diagnóstico creado correctamente", guardado);

        // Location: /diagnosticos/{id}
        URI location = URI.create("/diagnosticos/" + guardado.getId());
        return ResponseEntity.created(location).body(resp); // 201
    }

    // PUT /diagnosticos/{id}
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Diagnostico>> update(
            @PathVariable Long id, @Valid @RequestBody DiagnosticoRequestDto dto) {

        Diagnostico toUpdate = service.fromDto(dto);
        Diagnostico actualizado = service.update(id, toUpdate);

        ApiResponseSuccessDto<Diagnostico> resp =
                new ApiResponseSuccessDto<>(true, "Diagnóstico actualizado correctamente", actualizado);
        return ResponseEntity.ok(resp); // 200
    }

    // DELETE /diagnosticos/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<Void> resp =
                new ApiResponseSuccessDto<>(true, "Diagnóstico eliminado correctamente", null);
        return ResponseEntity.ok(resp); // 200
    }
}

