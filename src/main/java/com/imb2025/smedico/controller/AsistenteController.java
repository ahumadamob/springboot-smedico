package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.entity.AsistenteEntity;
import com.imb2025.smedico.service.Iasistente;

@RestController
@RequestMapping("/asistente")
public class AsistenteController {

    @Autowired
    private Iasistente service;

    @GetMapping
    public List<AsistenteEntity> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AsistenteEntity findById(@PathVariable("id") Long id) {
        return service.findById(id);
    }

    @PostMapping
    public AsistenteEntity save(@RequestBody AsistenteEntity asistenteEntity) {
        return service.save(asistenteEntity);
    }

    @PutMapping("/{id}")
    public AsistenteEntity update(@PathVariable("id") Long id, @RequestBody AsistenteEntity asistenteActualizado) {
        return service.update(id, asistenteActualizado);
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return "Asistente con ID " + id + " eliminado correctamente";
    }

}

