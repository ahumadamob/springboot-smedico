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
    public ResponseEntity<List<HistorialPaciente>> findAll() {
        List<HistorialPaciente> historiales = service.findAll();
        if (historiales.isEmpty()) {
            return ResponseEntity.noContent().build(); // 204
        } else {
            return ResponseEntity.ok(historiales); // 200
        }
    }

    // GET: Obtener historial por ID
    @GetMapping("/{idhistorialpaciente}")
    public ResponseEntity<HistorialPaciente> findById(@PathVariable Long idhistorialpaciente) {
        HistorialPaciente historial = service.findById(idhistorialpaciente);
        if (historial != null) {
            return ResponseEntity.ok(historial);
        } else {
            return ResponseEntity.noContent().build(); // 204
        }
    }

 // POST - Crear un nuevo historial de paciente usando DTO
    @PostMapping
    public ResponseEntity<HistorialPaciente> createHistorialPaciente(@RequestBody HistorialPacienteRequestDTO historialPacienteDto) throws Exception {
        HistorialPaciente nuevo = service.save(service.fromDto(historialPacienteDto));
        return ResponseEntity.ok(nuevo); // 200 OK
    }

    // PUT - Actualizar historial de paciente existente usando DTO
    @PutMapping("/{idhistorialpaciente}")
    public ResponseEntity<HistorialPaciente> updateHistorialPaciente(
            @RequestBody HistorialPacienteRequestDTO historialPacienteDto,
            @PathVariable("idhistorialpaciente") Long id) throws Exception {
        HistorialPaciente actualizado = service.update(id, service.fromDto(historialPacienteDto));
        return ResponseEntity.ok(actualizado); // 200 OK
    }


    // DELETE: Eliminar historial por ID
    @DeleteMapping("/{idhistorialpaciente}")
    public ResponseEntity<String> delete(@PathVariable Long idhistorialpaciente) {
        service.deleteById(idhistorialpaciente);
        return ResponseEntity.ok("Historial Paciente con ID " + idhistorialpaciente + " eliminado correctamente.");
    }
    
 // Manejador de excepciones global del controlador
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
