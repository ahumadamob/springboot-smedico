package com.imb2025.smedico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.imb2025.smedico.dto.EstadoTurnoRequestDTO;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.service.IEstadoTurnoService;

@RestController
@RequestMapping("/EstadoTurnoE") //Los endpoints definidos comenzarán con "/EstadoTurnoE"
public class EstadoTurnoController {

    private final IEstadoTurnoService estadoTurnoService; // Instanciamos EstadoTurnoS, para la lógica

    public EstadoTurnoController(IEstadoTurnoService estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }

    // GET de EstadoTurnoE (Obtener todos los registros por lista)
    @GetMapping
    public ResponseEntity<List<EstadoTurno>> getAll() {
        List<EstadoTurno> estados = estadoTurnoService.findAll();
        if (estados.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estados);
    }

    // GET de EstadoTurnoE {id} (Obtener un registro por ID)
    // Devuelve 204 (No Content) si el ID solicitado no existe
    @GetMapping("/{id}")
    public ResponseEntity<EstadoTurno> getById(@PathVariable Long id) {
        EstadoTurno estadoTurno = estadoTurnoService.findById(id); // Devuelve el objeto o null si no existe
        if (estadoTurno == null) {
            // Se devuelve 204 en lugar de 404 para indicar ausencia de contenido
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(estadoTurno);
    }

    // POST de EstadoTurnoE (Crear un nuevo registro)
    @PostMapping
    public ResponseEntity<EstadoTurno> create(@RequestBody EstadoTurnoRequestDTO dto) {
        EstadoTurno entidad = IEstadoTurnoService.fromDto(dto);
        EstadoTurno creado = estadoTurnoService.create(entidad);
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    /*Con ExceptionHandler interceptamos la excepcion y retornamos el mensaje*/
   
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarExcepcion(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<EstadoTurno> update(@PathVariable Long id, @RequestBody EstadoTurnoRequestDTO dto) {
        EstadoTurno estadoTurno = IEstadoTurnoService.fromDto(dto);
        EstadoTurno actualizado = estadoTurnoService.update(id, estadoTurno);

        return ResponseEntity.ok(actualizado);
    }



    
    @DeleteMapping("/{id}")
    /*Entrás al try.
    Verificás si el ID existe.
    Si no existe, lanza la excepción manualmente con throw new Exception(...).
    Esa excepción es capturada por el catch.
    El catch arma una respuesta clara: por ejemplo, un mensaje tipo
     "Error: No se encontró el ID" (texto o JSON)*/

    public ResponseEntity<String> delete(@PathVariable Long id) {
        if (estadoTurnoService.findById(id) == null) {
            return ResponseEntity.badRequest()
                    .body("Error: No se encontró el EstadoTurno con ID: " + id);
        }
        estadoTurnoService.deleteById(id);
        return ResponseEntity.ok("Eliminado correctamente");
    }

}

