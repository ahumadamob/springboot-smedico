package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.TurnoRequestDto;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.service.ITurnoService;

@RestController
public class TurnoController {

    @Autowired
    private ITurnoService service;
    


    // GET - Obtener todos los turnos
    @GetMapping("/turno")
    public ResponseEntity<List<Turno>> findAllTurnos() {
        List<Turno> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }

    // GET - Obtener turno por ID
    @GetMapping("/turno/{idturno}")
    public ResponseEntity<Turno> findTurnoById(@PathVariable("idturno") Long id) {
        Turno turno = service.findById(id);
        if (turno == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(turno);
    }

    @PostMapping("/turno")
    public ResponseEntity<Turno> create(@RequestBody TurnoRequestDto dto) throws Exception {
        Turno turno = service.fromDto(dto);
        return ResponseEntity.ok(service.create(turno));
    }

    @PutMapping("/turno/{idturno}")
    public ResponseEntity<Turno> update(@PathVariable("idturno") Long idturno, @RequestBody TurnoRequestDto dto) throws Exception {
        Turno turno = service.fromDto(dto);
        return ResponseEntity.ok(service.update(idturno, turno));
    }

    // DELETE - Eliminar turno
    @DeleteMapping("/turno/{idturno}")
    public ResponseEntity<String> deleteTurno(@PathVariable("idturno") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Turno " + id + " eliminado correctamente.");
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());

    }

 
    
    
}
