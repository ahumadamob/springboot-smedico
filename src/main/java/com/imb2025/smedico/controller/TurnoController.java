package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Turno createTurno(@RequestBody Turno turno) {
        return service.save(turno);
    }

    @PutMapping("/turno/{idturno}")
    public Turno updateTurno(@PathVariable("idturno") Long idturno, @RequestBody Turno datosTurno) {
        Turno turnoExistente = service.findById(idturno);

        // Validación simple por si no existe
        if (turnoExistente == null) {
            throw new RuntimeException("Turno no encontrado con ID: " + idturno);
        }

        // Actualizamos los campos
        turnoExistente.setFecha(datosTurno.getFecha());
        turnoExistente.setHora(datosTurno.getHora());
        turnoExistente.setPacienteId(datosTurno.getPacienteId());
        turnoExistente.setMedicoId(datosTurno.getMedicoId());
        turnoExistente.setEstadoTurnoId(datosTurno.getEstadoTurnoId());

        return service.save(turnoExistente);
    }


    // DELETE - Eliminar turno
    @DeleteMapping("/turno/{idturno}")
    public String deleteTurno(@PathVariable("idturno") Long id) {
        service.deleteById(id);
        return "Turno " + id + " eliminado correctamente.";
    }
}
