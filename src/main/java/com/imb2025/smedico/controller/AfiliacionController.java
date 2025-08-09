package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.service.IAfiliacionService;
import com.imb2025.smedico.dto.AfiliacionRequestDTO;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.repository.PacienteRepository;

@RestController
@RequestMapping("/Afiliacion")
public class AfiliacionController {

    @Autowired
    private IAfiliacionService servi;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private ObraSocialRepository obraSocialRepository;

    @GetMapping
    public ResponseEntity<List<Afiliacion>> findAllAfiliacion() {
        List<Afiliacion> afiliaciones = servi.findAll();
        return ResponseEntity.ok(afiliaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afiliacion> findAfiliacionById(@PathVariable("id") Long idAfiliacion) {
        Afiliacion afiliacion = servi.findById(idAfiliacion);
        return ResponseEntity.ok(afiliacion);
    }

    @PostMapping
    public ResponseEntity<Afiliacion> createAfiliacion(@RequestBody AfiliacionRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
                .orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));

        Afiliacion afiliacion = AfiliacionRequestDTO.fromDTO(dto, paciente, obraSocial);
        Afiliacion created = servi.save(afiliacion);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Afiliacion> updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
                .orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));

        Afiliacion afiliacion = AfiliacionRequestDTO.fromDTO(dto, paciente, obraSocial);
        Afiliacion updated = servi.update(id, afiliacion);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAfiliacion(@PathVariable Long id) {
        servi.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
