package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.OrdenEstudioRequestDTO;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.service.IOrdenEstudioService;

@RestController
@RequestMapping("/ordenestudio") // agrupás todos los endpoints
public class OrdenEstudioController {

    @Autowired
    private IOrdenEstudioService service;

    // GET - Obtener todas las órdenes de estudio
    @GetMapping
    public List<OrdenEstudio> findAllOrdenEstudio() {
        return service.findAll();
    }

    // GET - Obtener una orden de estudio por ID
    @GetMapping("/{id}")
    public OrdenEstudio findOrdenEstudioById(@PathVariable Long id) {
        return service.findById(id);
    }

    // POST - Crear una nueva orden de estudio usando DTO
    @PostMapping
    public OrdenEstudio createOrdenEstudio(@RequestBody OrdenEstudioRequestDTO ordenestudioDto) {
        try {
            return service.create(service.fromDto(ordenestudioDto));
            
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // PUT - Actualizar una orden de estudio existente usando DTO
    @PutMapping("/{idordenestudio}")
    public OrdenEstudio updateOrdenEstudio(@RequestBody OrdenEstudioRequestDTO ordenEstudioRequestDto,@PathVariable("idordenestudio") Long id) {
        try {
            return service.update(id,service.fromDto(ordenEstudioRequestDto));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // DELETE - Eliminar una orden de estudio
    @DeleteMapping("/{id}")
    public String deleteOrdenEstudio(@PathVariable Long id) {
        service.deleteById(id);
        return "Orden Estudio " + id + " eliminada correctamente.";
    }
}
