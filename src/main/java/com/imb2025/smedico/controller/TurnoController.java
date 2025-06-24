package com.imb2025.smedico.controller;

import java.util.List;
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
    public List<Turno> findAllTurnos() {
        return service.findAll();
    }

    // GET - Obtener turno por ID
    @GetMapping("/turno/{idturno}")
    public Turno findTurnoById(@PathVariable("idturno") Long id) {
        return service.findById(id);
    }

    @PostMapping("/turno")
    public Turno create(@RequestBody TurnoRequestDTO dto) {
        try {
            Turno turno = convertirDtoAEntidad(dto);
            return service.create(turno);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear turno: " + e.getMessage());
        }
    }

    @PutMapping("/turno/{idturno}")
    public Turno update(@PathVariable("idturno") Long idturno, @RequestBody TurnoRequestDTO dto) {
        try {
            Turno turno = convertirDtoAEntidad(dto);
            return service.update(idturno, turno);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar turno: " + e.getMessage());
        }
    }

    private Turno convertirDtoAEntidad(TurnoRequestDTO dto) throws Exception {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new Exception("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new Exception("Médico no encontrado"));
        EstadoTurno estado = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
            .orElseThrow(() -> new Exception("Estado turno no encontrado"));
        
        return new Turno(dto.getFecha(), dto.getHora(), paciente, medico, estado);
    }



    // DELETE - Eliminar turno
    @DeleteMapping("/turno/{idturno}")
    public String deleteTurno(@PathVariable("idturno") Long id) {
        service.deleteById(id);
        return "Turno " + id + " eliminado correctamente.";
    }
}
