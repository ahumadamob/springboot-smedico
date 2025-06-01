package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.HistorialPacienteRequestDTO;
import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.service.IHistorialPacienteService;

@RestController
@RequestMapping("/historialpaciente")
public class HistorialPacienteController {

    @Autowired
    private IHistorialPacienteService service;

    // GET: Obtener todos los historiales
    @GetMapping
    public List<HistorialPaciente> findAll() {
        return service.findAll();
    }

    // GET: Obtener historial por ID
    @GetMapping("/{idhistorialpaciente}")
    public ResponseEntity<HistorialPaciente> findById(@PathVariable Long idhistorialpaciente) {
        HistorialPaciente historial = service.findById(idhistorialpaciente);
        if (historial != null) {
            return ResponseEntity.ok(historial);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

 // POST - Crear un nuevo historial de paciente usando DTO
    @PostMapping
    public HistorialPaciente createHistorialPaciente(@RequestBody HistorialPacienteRequestDTO historialPacienteDto) {
        try {
            return service.save(service.fromDto(historialPacienteDto));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // PUT - Actualizar historial de paciente existente usando DTO
    @PutMapping("/{idhistorialpaciente}")
    public HistorialPaciente updateHistorialPaciente(@RequestBody HistorialPacienteRequestDTO historialPacienteDto,
                                                     @PathVariable("idhistorialpaciente") Long id) {
        try {
            return service.update(id, service.fromDto(historialPacienteDto));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    // DELETE: Eliminar historial por ID
    @DeleteMapping("/{idhistorialpaciente}")
    public ResponseEntity<String> delete(@PathVariable Long idhistorialpaciente) {
        service.deleteById(idhistorialpaciente);
        return ResponseEntity.ok("Historial Paciente con ID " + idhistorialpaciente + " eliminado correctamente.");
    }
}
