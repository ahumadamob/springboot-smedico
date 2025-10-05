package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.service.IEstadoTurnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estado-turno") // Los endpoints definidos comenzarán con "/estado-turno"
public class EstadoTurnoController {

    private final IEstadoTurnoService estadoTurnoService;

    public EstadoTurnoController(IEstadoTurnoService estadoTurnoService) {
        this.estadoTurnoService = estadoTurnoService;
    }
    
    // ------------------------------------------------------------------
    // TP07: Endpoint para Filtrar (findBy...)
    // URL de ejemplo: GET /estado-turno?filtro=pen
    // ------------------------------------------------------------------

    /**
     * Endpoint para filtrar EstadosTurno por una subcadena en su nombre.
     * Se activa cuando la URL incluye el parámetro 'filtro' y NO 'count'.
     * @param filtro Cadena de texto a buscar en el nombre (case-insensitive).
     * @return Respuesta estándar con la lista de DTOs que coinciden.
     */
    @GetMapping(params = {"filtro", "!count"}) // Se ejecuta si está 'filtro' pero NO 'count'
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> findByFiltro(@RequestParam(name = "filtro") String filtro) {
        // Usamos el método del servicio que llama al findByNombreContainingIgnoreCase del Repository
        List<EstadoTurno> estados = estadoTurnoService.findByNombreContaining(filtro);

        // Convertimos las entidades filtradas a DTOs
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre())) 
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, 
                        "Listado de EstadosTurno filtrado por '" + filtro + "' obtenido correctamente.", 
                        dtos);

        return ResponseEntity.ok(response);
    }
    
    // ------------------------------------------------------------------
    // TP07: Endpoint para Contar (countBy...)
    // URL de ejemplo: GET /estado-turno?count=Pendiente
    // ------------------------------------------------------------------

    /**
     * Endpoint para obtener el conteo de EstadosTurno con un nombre específico.
     * Se activa cuando la URL incluye el parámetro 'count' y NO 'filtro'.
     * @param nombre Nombre exacto a contar (case-insensitive).
     * @return Respuesta estándar con el número de coincidencias.
     */
    @GetMapping(params = {"count", "!filtro"}) // Se ejecuta si está 'count' pero NO 'filtro'
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByNombre(@RequestParam(name = "count") String nombre) {
        // Usamos el método del servicio que llama al countByNombreIgnoreCase del Repository
        long conteo = estadoTurnoService.countByNombre(nombre);

        ApiResponseSuccessDto<Long> response =
                new ApiResponseSuccessDto<>(true, 
                        "Conteo de EstadosTurno con nombre '" + nombre + "' obtenido correctamente.", 
                        conteo);

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------------
    // Métodos CRUD originales
    // ------------------------------------------------------------------

    // GET de EstadoTurnoE (Obtener todos los registros por lista)
    // *** CRUCIAL: Se ejecuta SOLO si NO están 'filtro' ni 'count' ***
    @GetMapping(params = {"!filtro", "!count"}) 
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> getAll() {
        List<EstadoTurno> estados = estadoTurnoService.findAll();

        // Convertimos la lista de entidades a lista de DTOs de respuesta
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre())) 
                .toList();

        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno obtenido correctamente", dtos);

        return ResponseEntity.ok(response);
    }

    // GET de EstadoTurnoE {id} (Obtener un registro por ID)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> getById(@PathVariable Long id) {
        EstadoTurno estadoTurno = estadoTurnoService.findById(id);

        EstadoTurnoRequestDto dto = new EstadoTurnoRequestDto(estadoTurno.getId(), estadoTurno.getNombre());
        
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

        EstadoTurno entidad = estadoTurnoService.fromDto(dto);

        EstadoTurno creado = estadoTurnoService.create(entidad);

        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(creado.getId(), creado.getNombre());

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno creado correctamente", responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid EstadoTurnoRequestDto dto) { 

        EstadoTurno estadoTurno = estadoTurnoService.fromDto(dto);
        EstadoTurno actualizado = estadoTurnoService.update(id, estadoTurno);

        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(
                actualizado.getId(),
                actualizado.getNombre()
        );

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno actualizado correctamente", responseDto);

        return ResponseEntity.ok(response);
    }

    // DELETE de EstadoTurnoE (Eliminar un registro)
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


