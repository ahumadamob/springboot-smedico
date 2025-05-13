package com.imb2025.smedico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.imb2025.smedico.entity.HorarioAtencionEntity;
import com.imb2025.smedico.service.HorarioAtencionService;

@RestController
@RequestMapping("/horarioAtencion")
public class HorarioAtencionController {

    @Autowired
    private HorarioAtencionService horarioAtencionService;

    @GetMapping
    public List<HorarioAtencionEntity> getAllHorarioAtencion() {
        return horarioAtencionService.getAllHorarioAtencion();
    }

    @GetMapping("/{id}")
    public HorarioAtencionEntity getHorarioAtencionById(@PathVariable Long id) {
        return horarioAtencionService.getHorarioAtencionById(id);
    }

    @PostMapping
    public HorarioAtencionEntity createHorarioAtencion(@RequestBody HorarioAtencionEntity horarioAtencionEntity) {
        return horarioAtencionService.createHorarioAtencion(horarioAtencionEntity);
    }

    @PutMapping("/{id}")
    public HorarioAtencionEntity updateHorarioAtencion(@PathVariable Long id, @RequestBody HorarioAtencionEntity horarioAtencionEntity) {
        return horarioAtencionService.updateHorarioAtencion(id, horarioAtencionEntity);
    }
    
    @DeleteMapping("/{id}")
    public String deleteHorarioAtencion(@PathVariable Long id) {
        horarioAtencionService.deleteHorarioAtencion(id);
        return "Afiliacion " + id.toString() + " eliminada correctamente.";
    }
}