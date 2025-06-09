package com.imb2025.smedico.controller;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.TurnoRequestDTO;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.ITurnoService;

@RestController
public class TurnoController {

    @Autowired
    private ITurnoService service;
    
    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EstadoTurnoRepository estadoTurnoRepository;


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
    public ResponseEntity<Turno> create(@RequestBody TurnoRequestDTO dto) throws Exception {
        Turno turno = convertirDtoAEntidad(dto);
        return ResponseEntity.ok(service.create(turno));

    }

    @PutMapping("/turno/{idturno}")
    public ResponseEntity<Turno> update(@PathVariable("idturno") Long idturno, @RequestBody TurnoRequestDTO dto) throws Exception {
        Turno turno = convertirDtoAEntidad(dto);
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

    private Turno convertirDtoAEntidad(TurnoRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new RuntimeException("Médico no encontrado"));
        EstadoTurno estado = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
            .orElseThrow(() -> new RuntimeException("Estado turno no encontrado"));

        return new Turno(dto.getFecha(), dto.getHora(), paciente, medico, estado);
    }
    
 
    
    
}