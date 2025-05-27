package com.imb2025.smedico.controller;
//Controlador
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.AsistenteRequestDTO;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.service.IAsistenteService;

@RestController
@RequestMapping("/asistente")
public class AsistenteController {

    @Autowired
    private IAsistenteService service;

    @GetMapping
    public List<Asistente> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Asistente findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Asistente save(@RequestBody AsistenteRequestDTO dto) {
        return service.create(dto);
    }

    @PutMapping("/{id}")
    public Asistente update(@PathVariable("id") Long id, @RequestBody AsistenteRequestDTO dto) {
        return service.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "Asistente con ID " + id + " eliminado correctamente";
    }
    
    @ExceptionHandler(RuntimeException.class)
    public Map<String, Object> handleRuntimeException(RuntimeException ex) {
        Map<String, Object> errorMap = new HashMap<>();
        errorMap.put("message", ex.getMessage());
        return errorMap;
    }
}
