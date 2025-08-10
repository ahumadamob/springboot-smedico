package com.imb2025.smedico.controller;



import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @GetMapping
    public ResponseEntity<List<Consulta>> findAll() {
        return ResponseEntity.ok(consultaService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Consulta> getById(@PathVariable Long id) {
        Consulta consulta = consultaService.findById(id);
        return ResponseEntity.ok(consulta);
    }

    @PostMapping
    public ResponseEntity<Consulta> create(@RequestBody ConsultaRequestDto dto) {
        Consulta consulta = consultaService.fromDto(dto);
        Consulta creada = consultaService.save(consulta);
        return ResponseEntity.status(201).body(creada);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Consulta> update(@PathVariable Long id, @RequestBody ConsultaRequestDto dto) {
        Consulta consulta = consultaService.fromDto(dto);
        Consulta actualizada = consultaService.update(id, consulta);
        return ResponseEntity.ok(actualizada);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        consultaService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
}

