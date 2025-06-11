package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.service.IObraSocialService;
import com.imb2025.smedico.dto.ObraSocialRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/obrasocial")
public class ObraSocialController {

    @Autowired
    private IObraSocialService service;

    
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<ObraSocial> obras = service.findAll();
        if (obras.isEmpty()) {
            return ResponseEntity.status(HttpStatus.OK).body("Sin datos: no hay obras sociales registradas.");
        }
        return ResponseEntity.ok(obras);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<ObraSocial> getById(@PathVariable Long id) {
        ObraSocial obraSocial = service.findById(id)
                .orElseThrow(() -> new RuntimeException("No existe obra social para el ID: " + id));
        return ResponseEntity.ok(obraSocial);
    }

    
    @PostMapping
    public ResponseEntity<Map<String, Object>> createObraSocial(@RequestBody ObraSocialRequestDTO dto) throws Exception {
        ObraSocial obra = service.create(service.fromDto(dto));
        
        Map<String, Object> response = new HashMap<>();
        response.put("Perfecto", "Obra social agregada con éxito");
        response.put("obraSocial", obra);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


   
    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarObraSocial(@PathVariable Long id, @RequestBody ObraSocialRequestDTO dto) throws Exception {
        ObraSocial obraSocial = service.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró una obra social con el ID: " + id));

        obraSocial.setNombre(dto.getNombre());
        obraSocial.setTelefono(dto.getTelefono());
        obraSocial.setDireccion(dto.getDireccion());
        obraSocial.setCobertura(dto.getCobertura());

        service.update(id, obraSocial);

        Map<String, Object> response = new HashMap<>();
        response.put("mensaje", "Datos actualizados correctamente");
        response.put("obraSocial", obraSocial);

        return ResponseEntity.ok(response);
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.findById(id)
                .orElseThrow(() -> new RuntimeException("No se encontró una obra social con el ID: " + id));

        service.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleRuntime(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGeneral(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body("Error interno: " + ex.getMessage());
    }
}


