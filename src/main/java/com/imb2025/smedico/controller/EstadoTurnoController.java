package com.imb2025.smedico.controller;

import org.springframework.web.bind.annotation.*;

import java.util.List;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.service.IEstadoTurnoService;

@RestController
@RequestMapping("/EstadoTurnoE") //Los endpoints definidos comenzarán con "/EstadoTurnoE"
public class EstadoTurnoController {

    private final IEstadoTurnoService estadoTurnoService; // Instanciamos EstadoTurnoS, para la lógica

    public EstadoTurnoController(IEstadoTurnoService estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }

    // GET de EstadoTurnoE (Obtener todos los registros)
    @GetMapping
    public List<EstadoTurno> getAll() {
        return estadoTurnoService.findAll();
    }

    // GET de EstadoTurnoE {id} (Obtener un registro por ID)
    @GetMapping("/{id}")
    public EstadoTurno getById(@PathVariable Long id) {
        return estadoTurnoService.findById(id); // Devuelve el objeto o null si no existe
    }

    // POST de EstadoTurnoE (Crear un nuevo registro)
    @PostMapping
    public EstadoTurno create(@RequestBody EstadoTurno estadoTurno) {
        return estadoTurnoService.save(estadoTurno);
    }

    // PUT de EstadoTurnoE{id} (Actualizar un registro existente)
    @PutMapping
    public EstadoTurno update(@RequestBody EstadoTurno estadoTurno) {
        return estadoTurnoService.save(estadoTurno);
    }

    // DELETE de EstadoTurnoE{id} (Eliminar un registro)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        estadoTurnoService.deleteById(id);
    }
}
