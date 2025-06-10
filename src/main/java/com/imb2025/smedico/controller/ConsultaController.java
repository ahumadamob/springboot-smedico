package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ConsultaRequestDTO;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.service.IConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @GetMapping
    public List<Consulta> getAll() {
        return consultaService.findAll();
    }

    @GetMapping("/{id}")
    public Consulta getById(@PathVariable Long id) {
        return consultaService.findById(id);
    }

    @PostMapping
    public Consulta create(@RequestBody ConsultaRequestDTO dto) {
        return consultaService.save(dto);
    }

    @PutMapping("/{id}")
    public Consulta update(@PathVariable Long id, @RequestBody ConsultaRequestDTO dto) {
        return consultaService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        consultaService.deleteById(id);
    }
}
