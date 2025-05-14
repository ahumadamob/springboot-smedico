package com.imb2025.smedico.ControllerET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import com.imb2025.smedico.EntityET.EstadoTurnoE;
import com.imb2025.smedico.ServicieET.EstadoTurnoS;

@RestController
@RequestMapping("/EstadoTurnoE") //Los endpoints definidos comenzarán con "/EstadoTurnoE"
public class EstadoTurnoC {

    private final EstadoTurnoS estadoTurnoService; // Instanciamos EstadoTurnoS, para la lógica

    public EstadoTurnoC(EstadoTurnoS estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }

    // GET de EstadoTurnoE (Obtener todos los registros)
    @GetMapping
    public List<EstadoTurnoE> getAll() {
        return estadoTurnoService.findAll();
    }

    // GET de EstadoTurnoE {id} (Obtener un registro por ID)
    @GetMapping("/{id}")
    public EstadoTurnoE getById(@PathVariable Long id) {
        return estadoTurnoService.findById(id).orElse(null); // Devuelve el objeto o null si no existe
    }

    // POST de EstadoTurnoE (Crear un nuevo registro)
    @PostMapping
    public EstadoTurnoE create(@RequestBody EstadoTurnoE estadoTurno) {
        return estadoTurnoService.save(estadoTurno);
    }

    // PUT de EstadoTurnoE{id} (Actualizar un registro existente)
    @PutMapping("/{id}")
    public EstadoTurnoE update(@PathVariable Long id, @RequestBody EstadoTurnoE estadoTurno) {
        return estadoTurnoService.update(id, estadoTurno);
    }

    // DELETE de EstadoTurnoE{id} (Eliminar un registro)
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        estadoTurnoService.deleteById(id);
    }
}
