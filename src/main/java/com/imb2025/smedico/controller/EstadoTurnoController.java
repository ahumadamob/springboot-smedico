package com.imb2025.smedico.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @GetMapping("/{id}")
    public ResponseEntity<EstadoTurno> getById(@PathVariable Long id) {
        EstadoTurno estadoTurno = estadoTurnoService.findById(id); // Devuelve el objeto o null si no existe
        if (estadoTurno == null) {
            return ResponseEntity.notFound().build();
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

    /*Con ExceptionHandler interceptamos la excepcion, se crea el map que es una estructura_
    tipo diccionario, es decir una coleccion de pares "clave, valor"
    en este caso seria "mensaje"(clave): "El id colocado no existe"(valor), de esta manera
    podemos mandaar mensajes personalizados por json a postman"*/
   
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Map<String, String>> manejarExcepcion(RuntimeException ex) {
        Map<String, String> error = new HashMap<>();
        error.put("mensaje", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
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

