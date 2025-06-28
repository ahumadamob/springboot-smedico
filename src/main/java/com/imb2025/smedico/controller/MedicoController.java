package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.MedicoRequestDTO;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.service.IMedicoService;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    private IMedicoService service;

    @GetMapping
    public ResponseEntity<List<Medico>> findAllMedicos() {
        List<Medico> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build(); 
        }
        return ResponseEntity.ok(lista); 
    }

    @GetMapping("/{idmedico}")
    public ResponseEntity<Medico> findById(@PathVariable("idmedico") Long id) {
        Medico medico = service.findById(id);
        if (medico == null) {
            return ResponseEntity.noContent().build();  
        }
        return ResponseEntity.ok(medico);  
    }

    @PostMapping
    public ResponseEntity<Medico> create(@RequestBody MedicoRequestDTO dto) throws Exception {
        Medico medico = service.fromDto(dto);
        Medico creado = service.create(medico);
        return ResponseEntity.ok(creado); 
    }

    @PutMapping("/{idmedico}")
    public ResponseEntity<Medico> update(@PathVariable("idmedico") Long id, @RequestBody MedicoRequestDTO dto) throws Exception {
        Medico medico = service.fromDto(dto);
        Medico actualizado = service.update(id, medico);
        return ResponseEntity.ok(actualizado); 
    }

    @DeleteMapping("/{idmedico}")
    public ResponseEntity<String> delete(@PathVariable("idmedico") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Médico con ID " + id + " eliminado correctamente."); 
    }

  
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalExceptions(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
