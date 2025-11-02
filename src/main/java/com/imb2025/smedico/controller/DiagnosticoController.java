package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.DiagnosticoRequestDto;
import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.service.IDiagnosticoService;

import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
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

    // GET /diagnosticos → lista todos
    @GetMapping
    public ResponseEntity<List<Diagnostico>> getAll() {
        List<Diagnostico> data = service.findAll();
        if (data.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 si no hay registros
        }
        return ResponseEntity.ok(data); // 200 OK con la lista
    }

    // GET /diagnosticos/{id} → busca por id
    @GetMapping("/{id}")
    public ResponseEntity<Diagnostico> getById(@PathVariable Long id) {
        Diagnostico data = service.findById(id);
        if (data == null) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
        return ResponseEntity.ok(data); // 200 OK con el diagnóstico
    }

    // POST /diagnosticos → crea uno nuevo
    @PostMapping
    public ResponseEntity<Diagnostico> create(@Valid @RequestBody DiagnosticoRequestDto dto) {
        Diagnostico nuevo = service.fromDto(dto);
        Diagnostico guardado = service.create(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardado); // 201 Created
    }

    // PUT /diagnosticos/{id} → actualiza un diagnóstico
    @PutMapping("/{id}")
    public ResponseEntity<Diagnostico> update(
            @PathVariable Long id,
            @Valid @RequestBody DiagnosticoRequestDto dto) {

        Diagnostico actualizado = service.update(id, service.fromDto(dto));
        if (actualizado == null) {
            return ResponseEntity.notFound().build(); // 404 si no existe
        }
        return ResponseEntity.ok(actualizado); // 200 OK con el actualizado
    }

    // DELETE /diagnosticos/{id} → elimina por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // 204 sin contenido
    }


    // GET /diagnosticos/find/{fecha} → busca diagnósticos por fecha
    @GetMapping("/find/{fecha}")
    public ResponseEntity<List<Diagnostico>> getDiagnosticosPorFecha(
            @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        List<Diagnostico> lista = service.findByFechaDiagnostico(fecha);
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204 si no hay
        }
        return ResponseEntity.ok(lista);
    }

    // GET /diagnosticos/count/{fecha} → cuenta diagnósticos por fecha
    @GetMapping("/count/{fecha}")
    public ResponseEntity<Long> countDiagnosticosPorFecha(
            @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fecha) {

        long cantidad = service.countByFechaDiagnostico(fecha);
        return ResponseEntity.ok(cantidad); // 200 OK con el número
    }
}
