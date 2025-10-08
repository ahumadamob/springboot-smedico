package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.service.IEstadoTurnoService;

import jakarta.validation.Valid;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/estado-turno") //Los endpoints definidos comenzarán con "/estado-turno"
public class EstadoTurnoController {

    private final IEstadoTurnoService estadoTurnoService;

    public EstadoTurnoController(IEstadoTurnoService estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }

    // GET de EstadoTurnoE (Obtener todos los registros por lista)
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> getAll() {
        List<EstadoTurno> estados = estadoTurnoService.findAll();

        // Convertimos la lista de entidades a lista de DTOs de respuesta con ID
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre()))
                .collect(Collectors.toList());

        // Envolvemos todo en el DTO estándar de éxito
        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno obtenido correctamente", dtos);

        return ResponseEntity.ok(response);
    }

    // GET de EstadoTurnoE {id} (Obtener un registro por ID)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> getById(@PathVariable Long id) {
        // Buscamos la entidad, el servicio lanzará ResourceNotFoundException si no existe
        EstadoTurno estadoTurno = estadoTurnoService.findById(id);

        // Convertimos la entidad a DTO incluyendo el ID
        EstadoTurnoRequestDto dto = new EstadoTurnoRequestDto(estadoTurno.getId(), estadoTurno.getNombre());

        // Armamos la respuesta genérica
        ApiResponseSuccessDto<EstadoTurnoRequestDto> response = new ApiResponseSuccessDto<>(
                true,
                "EstadoTurno encontrado",
                dto
        );

        return ResponseEntity.ok(response);
    }

    // POST de EstadoTurnoE (Crear un nuevo registro)
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> create(
            @Valid @RequestBody EstadoTurnoRequestDto dto) {

        // conversión DTO a entidad y persistencia
        EstadoTurno entidad = estadoTurnoService.fromDto(dto);
        EstadoTurno creado = estadoTurnoService.create(entidad);

        // DTO de respuesta con el ID del nuevo recurso
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(creado.getId(), creado.getNombre());

        // respuesta estándar
        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno creado correctamente", responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT de EstadoTurnoE (Actualizar un nuevo registro)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody EstadoTurnoRequestDto dto) {

        EstadoTurno estadoTurno = estadoTurnoService.fromDto(dto);
        EstadoTurno actualizado = estadoTurnoService.update(id, estadoTurno);

        // Reutilizamos el mismo DTO para la respuesta, incluyendo el ID
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(actualizado.getId(), actualizado.getNombre());

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno actualizado correctamente", responseDto);

        return ResponseEntity.ok(response);
    }

 // DELETE de EstadoTurnoE (Eliminar un registro)
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        estadoTurnoService.deleteById(id);
        ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>(
                true,
                "EstadoTurno eliminado correctamente",
                null
        );
        return ResponseEntity.ok(response);
    }
}
