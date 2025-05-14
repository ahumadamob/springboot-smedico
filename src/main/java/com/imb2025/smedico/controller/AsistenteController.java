package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public Asistente save(@RequestBody Asistente asistenteEntity) {
        return service.save(asistenteEntity);
    }

    @PutMapping("/{id}")
    public Asistente update(@PathVariable("id") Long id, @RequestBody Asistente asistenteActualizado) {
        return service.update(id, asistenteActualizado);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "Asistente con ID " + id + " eliminado correctamente";
    }

}

