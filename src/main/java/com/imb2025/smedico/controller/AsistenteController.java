package com.imb2025.smedico.controller;
//Controller

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.AsistenteRequestDTO;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.service.IAsistenteService;

@RestController
@RequestMapping("/asistentes")
public class AsistenteController {

    @Autowired
    private IAsistenteService service;


    @GetMapping
    public ResponseEntity<List<Asistente>> findAll() {
        List<Asistente> lista = service.findAll();
        return lista.isEmpty()
                ? ResponseEntity.noContent().build()           
                : ResponseEntity.ok(lista);                    
    }

    @GetMapping("/{id}")
    public ResponseEntity<Asistente> findById(@PathVariable Long id) {
        Asistente asistente = service.findById(id);            
        return ResponseEntity.ok(asistente);                   
    }

    @PostMapping
    public ResponseEntity<Asistente> create(@RequestBody AsistenteRequestDTO dto) {
        Asistente creado = service.create(dto);
        return ResponseEntity.ok(creado);                     
    }

    @PutMapping("/{id}")
    public ResponseEntity<Asistente> update(@PathVariable Long id,
                                            @RequestBody AsistenteRequestDTO dto) {
        Asistente actualizado = service.update(id, dto);      
        return ResponseEntity.ok(actualizado);                 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.deleteById(id);                                
        return ResponseEntity.ok().build();                  
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());  // 400
    }
}
