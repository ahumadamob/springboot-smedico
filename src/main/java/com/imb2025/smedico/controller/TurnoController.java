package com.imb2025.smedico.controller;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.TurnoRequestDTO;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.service.ITurnoService;

@RestController
public class TurnoController {

    @Autowired
    private ITurnoService service;

    // GET - Obtener todos los turnos
    @GetMapping("/turno")
    public List<Turno> findAllTurnos() {
        return service.findAll();
    }

    // GET - Obtener turno por ID
    @GetMapping("/turno/{idturno}")
    public Turno findTurnoById(@PathVariable("idturno") Long id) {
        return service.findById(id);
    }

    // POST - Crear nuevo turno
    @PostMapping("/turno")
    public Turno createTurno(@RequestBody TurnoRequestDTO turnoRequestDTOurno) {
    	   try {
    	        return service.save(service.fromDto(turnoRequestDTOurno));
    	    } catch (Exception e) {
    	        throw new RuntimeException("Error al crear turno: " + e.getMessage());
    	    }
    }

    @PutMapping("/turno/{idturno}")
    public Turno updateTurno(@PathVariable("idturno") Long idturno, @RequestBody TurnoRequestDTO dto) {
        Turno turnoExistente = service.findById(idturno);

        if (turnoExistente == null) {
            throw new RuntimeException("Turno no encontrado con ID: " + idturno);
        }

        try {
            return service.updateFromDto(turnoExistente, dto);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar turno: " + e.getMessage());
        }
    }


    // DELETE - Eliminar turno
    @DeleteMapping("/turno/{idturno}")
    public String deleteTurno(@PathVariable("idturno") Long id) {
        service.deleteById(id);
        return "Turno " + id + " eliminado correctamente.";
    }
}
