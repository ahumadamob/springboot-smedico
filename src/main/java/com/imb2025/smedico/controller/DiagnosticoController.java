package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.service.IDiagnosticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.DiagnosticoRequestDTO;
import org.springframework.http.HttpStatus;


import java.util.List;

@RestController
@RequestMapping("/diagnostico")
public class DiagnosticoController {

    @Autowired
    private IDiagnosticoService service;

    @GetMapping
    public List<Diagnostico> getAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Diagnostico getById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("/diagnosticos")
    public ResponseEntity<?> crearDiagnostico(@RequestBody DiagnosticoRequestDTO dto) {
        Diagnostico nuevo = service.fromDto(dto);

        if (nuevo == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Consulta no encontrada");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(nuevo));
    }


    @PutMapping("/diagnosticos/{id}")
    public ResponseEntity<Diagnostico> actualizarDiagnostico(@PathVariable Long id, @RequestBody DiagnosticoRequestDTO dto) {
        Diagnostico actualizado = service.actualizar(id, dto);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteById(id);
    }
}
