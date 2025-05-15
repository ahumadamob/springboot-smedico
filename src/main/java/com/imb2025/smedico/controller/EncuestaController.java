package com.imb2025.smedico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.service.IEncuestaService;

import java.util.List;



@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {

    @Autowired
	private IEncuestaService service;

    @GetMapping
    public List<Encuesta> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Encuesta findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping
    public Encuesta save(@RequestBody Encuesta encuesta) {
        return service.save(encuesta);
    }

    @PutMapping("/{id}")
    public Encuesta update(@PathVariable Long id, @RequestBody Encuesta encuesta) {
    	return service.save(encuesta);
    }

    @DeleteMapping("/{id}")
    public String deleteById(@PathVariable Long id) {
    	service.deleteById(id);
		return "Encuesta " + id.toString() + " eliminado correctamente. ";
    }
}