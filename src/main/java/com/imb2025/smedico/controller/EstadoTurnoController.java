package com.imb2025.smedico.controller;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.service.IEstadoTurnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estado-turno") //Los endpoints definidos comenzarán con "/estado-turno"
public class EstadoTurnoController {

    private final IEstadoTurnoService estadoTurnoService; // Instanciamos EstadoTurnoS, para la lógica

    public EstadoTurnoController(IEstadoTurnoService estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }

    // GET de EstadoTurnoE (Obtener todos los registros por lista)
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> getAll() {
        List<EstadoTurno> estados = estadoTurnoService.findAll();

        // Convertimos la lista de entidades a lista de DTOs de respuesta
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getNombre()))
                .toList();

        // Envolvemos todo en el DTO estándar de éxito
        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno obtenido correctamente", dtos);

        return ResponseEntity.ok(response);
    }

    // GET de EstadoTurnoE {id} (Obtener un registro por ID)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> getById(@PathVariable Long id) {
        // Buscamos la entidad, lanzará ResourceNotFoundException si no existe
        EstadoTurno estadoTurno = estadoTurnoService.findById(id);

        // Convertimos la entidad a DTO
        EstadoTurnoRequestDto dto = new EstadoTurnoRequestDto();
        
        dto.setNombre(estadoTurno.getNombre());
        // No incluimos la colección 'turnos' para evitar LazyInitializationException
        // Armamos la respuesta genérica
        ApiResponseSuccessDto<EstadoTurnoRequestDto> response = new ApiResponseSuccessDto<>(
            true,       // success
            "EstadoTurno encontrado",  // mensaje
            dto         // data
        );

        return ResponseEntity.ok(response);
    }


    // POST de EstadoTurnoE (Crear un nuevo registro)
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> create(
            @Valid @RequestBody EstadoTurnoRequestDto dto) {

        // conversión DTO a entidad
        EstadoTurno entidad = estadoTurnoService.fromDto(dto);

        // persistencia
        EstadoTurno creado = estadoTurnoService.create(entidad);

        // DTO de respuesta
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(creado.getNombre());

        // respuesta estándar
        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno creado correctamente", responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> update(
            @PathVariable Long id,
            @RequestBody EstadoTurnoRequestDto dto) {

        EstadoTurno estadoTurno = estadoTurnoService.fromDto(dto);
        EstadoTurno actualizado = estadoTurnoService.update(id, estadoTurno);

        // Reutilizamos el mismo DTO para la respuesta
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(
                actualizado.getNombre()
        );

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno actualizado correctamente", responseDto);

        return ResponseEntity.ok(response);
    }



    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        if (!estadoTurnoService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseSuccessDto<>(
                            false,
                            "Error: No se encontró el EstadoTurno con ID: " + id,
                            null
                    ));
        }

        estadoTurnoService.deleteById(id);

        ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>(
                true,
                "EstadoTurno eliminado correctamente",
                null
        );

        return ResponseEntity.ok(response);
    }


}

