package com.imb2025.smedico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.service.IHorarioAtencionService;
import com.imb2025.smedicoDtoRequest.HorarioAtencionRequestDTO;

@RestController
@RequestMapping("/horarioAtencion")
public class HorarioAtencionController {

    @Autowired
    private IHorarioAtencionService horarioAtencionService;

    @GetMapping
    public List<HorarioAtencion> getAllHorarioAtencion() {
        return horarioAtencionService.getAllHorarioAtencion();
    }

    @GetMapping("/{id}")
    public HorarioAtencion getHorarioAtencionById(@PathVariable Long id) {
        return horarioAtencionService.getHorarioAtencionById(id);
    }

    @PostMapping
    public HorarioAtencion createHorarioAtencion(@RequestBody HorarioAtencionRequestDTO dto) throws Exception {
        return horarioAtencionService.create(dto);
    }

    @PutMapping("/{id}")
    public HorarioAtencion updateHorarioAtencion(@PathVariable Long id, @RequestBody HorarioAtencionRequestDTO dto) throws Exception {
        return horarioAtencionService.update(dto, id);
    }
    
    @DeleteMapping("/{id}")
    public String deleteHorarioAtencion(@PathVariable Long id) {
        horarioAtencionService.deleteHorarioAtencion(id);
        return "Afiliacion " + id.toString() + " eliminada correctamente.";
    }
}