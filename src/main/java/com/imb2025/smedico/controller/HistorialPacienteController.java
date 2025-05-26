package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.service.IHistorialPacienteService;

@RestController
@RequestMapping("/historialpaciente")
public class HistorialPacienteController {

    @Autowired
    private IHistorialPacienteService service;

    //GET: Obtener todos los historiales
    @GetMapping("/")
    public List<HistorialPaciente> findAll() {
        return service.findAll();
    }

    //GET: Obtener historial por ID
    @GetMapping("/{idhistorialpaciente}")
    public HistorialPaciente findById(@PathVariable("idhistorialpaciente") Long id) {
        return service.findById(id);
    }

    //POST: Crear nuevo historial
    @PostMapping("/")
    public HistorialPaciente create(@RequestBody HistorialPaciente historial) {
        return service.save(historial);
    }
    
    @PutMapping("/")
    public HistorialPaciente update(@RequestBody HistorialPaciente historial) {
        return service.save(historial);
    }    

    //DELETE: Eliminar historial por ID
    @DeleteMapping("/{idhistorialpaciente}")
    public String delete(@PathVariable("idhistorialpaciente") Long id) {
        service.deleteById(id);
        return "Historial Paciente con ID " + id + " eliminado correctamente.";
    }
}
