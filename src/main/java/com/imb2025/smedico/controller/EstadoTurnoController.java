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
    
    // ==================================================================
    // EJERCICIO 1: Nuevos Endpoints de Filtro Booleano (Rutas Separadas)
    // ==================================================================
    
    /**
     * Endpoint para obtener todos los EstadosTurno donde esFinal = TRUE.
     * Cumple el criterio: NO usa parametrización.
     * URL: GET /estado-turno/finales
     */
    @GetMapping("/finales")
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> findFinales() {
        // Llama al método del servicio que usa findByEsFinalTrue()
        List<EstadoTurno> estados = estadoTurnoService.findFinales();

        // Conversión de Entidad a DTO con todos los campos
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre(), e.getVersion(), e.getEsFinal()))
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno Finales (esFinal=TRUE) obtenido correctamente.", dtos);

        return ResponseEntity.ok(response);
    }
    
    /**
     * Endpoint para obtener todos los EstadosTurno donde esFinal = FALSE.
     * Cumple el criterio: NO usa parametrización.
     * URL: GET /estado-turno/pendientes
     */
    @GetMapping("/pendientes")
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> findPendientes() {
        // Llama al método del servicio que usa findByEsFinalFalse()
        List<EstadoTurno> estados = estadoTurnoService.findPendientes();

        // Conversión de Entidad a DTO con todos los campos
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre(), e.getVersion(), e.getEsFinal()))
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno Pendientes (esFinal=FALSE) obtenido correctamente.", dtos);

        return ResponseEntity.ok(response);
    }

    // ==================================================================
    // TP07: Métodos de Filtro y Conteo (Se mantienen en la ruta base con params)
    // ==================================================================

    /**
     * Endpoint para filtrar EstadosTurno por una subcadena en su nombre.
     * URL de ejemplo: GET /estado-turno?filtro=pen
     */
    @GetMapping(params = {"filtro", "!count"})
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> findByFiltro(@RequestParam(name = "filtro") String filtro) {
        List<EstadoTurno> estados = estadoTurnoService.findByNombreContaining(filtro);

        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre(), e.getVersion(), e.getEsFinal()))
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno filtrado por '" + filtro + "' obtenido correctamente.", dtos);

        return ResponseEntity.ok(response);
    }

    /**
     * Endpoint para obtener el conteo de EstadosTurno con un nombre específico.
     * URL de ejemplo: GET /estado-turno?count=Pendiente
     */
    @GetMapping(params = {"count", "!filtro"})
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByNombre(@RequestParam(name = "count") String nombre) {
        long conteo = estadoTurnoService.countByNombre(nombre);

        ApiResponseSuccessDto<Long> response =
                new ApiResponseSuccessDto<>(true, "Conteo de EstadosTurno con nombre '" + nombre + "' obtenido correctamente.", conteo);

        return ResponseEntity.ok(response);
    }

    // ==================================================================
    // Métodos CRUD (Se ajustan para la conversión de DTO con todos los campos)
    // ==================================================================

    /**
     * GET de EstadoTurnoE (Obtener todos los registros por lista)
     * Se ejecuta si NO están 'filtro' ni 'count'
     */
    @GetMapping(params = {"!filtro", "!count"})
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> getAll() {
        List<EstadoTurno> estados = estadoTurnoService.findAll();

        List<EstadoTurnoRequestDto> dtos = estados.stream()
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre(), e.getVersion(), e.getEsFinal()))
                .toList();

        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno obtenido correctamente", dtos);

        return ResponseEntity.ok(response);
    }

    /**
     * GET de EstadoTurnoE {id} (Obtener un registro por ID)
     */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> getById(@PathVariable Long id) {
        EstadoTurno estadoTurno = estadoTurnoService.findById(id);

        EstadoTurnoRequestDto dto = new EstadoTurnoRequestDto(estadoTurno.getId(), estadoTurno.getNombre(), estadoTurno.getVersion(), estadoTurno.getEsFinal());

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response = new ApiResponseSuccessDto<>(
                true,
                "EstadoTurno encontrado",
                dto
        );

        return ResponseEntity.ok(response);
    }

    /**
     * POST de EstadoTurnoE (Crear un nuevo registro)
     */
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> create(
            @Valid @RequestBody EstadoTurnoRequestDto dto) {

        // Conversión DTO a entidad (usando el fromDto del servicio, ya que el Mapper no existe en esta rama)
        EstadoTurno entidad = estadoTurnoService.fromDto(dto);

        // persistencia
        EstadoTurno creado = estadoTurnoService.create(entidad);

        // DTO de respuesta con ID, Version y esFinal
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(creado.getId(), creado.getNombre(), creado.getVersion(), creado.getEsFinal());

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno creado correctamente", responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    /**
     * PUT de EstadoTurnoE (Actualizar un registro)
     */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid EstadoTurnoRequestDto dto) {

        // Conversión DTO a entidad (usando el fromDto del servicio)
        EstadoTurno entidad = estadoTurnoService.fromDto(dto);
        
        // Se asegura que la entidad tenga el ID para el service.update
        entidad.setId(id);

        // Se usa la versión del DTO para el control de concurrencia optimista
        if (dto.getVersion() != null) {
            entidad.setVersion(dto.getVersion());
        }

        EstadoTurno actualizado = estadoTurnoService.update(id, entidad);

        // DTO de respuesta con la versión actualizada
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(
                actualizado.getId(),
                actualizado.getNombre(),
                actualizado.getVersion(),
                actualizado.getEsFinal()
        );

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno actualizado correctamente", responseDto);

        return ResponseEntity.ok(response);
    }

    /**
     * DELETE de EstadoTurnoE (Eliminar un registro)
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        // Intentar eliminar, la lógica de chequeo está en el servicio
        try {
            estadoTurnoService.deleteById(id);
            ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>(
                true,
                "EstadoTurno eliminado correctamente",
                null
            );
            return ResponseEntity.ok(response);
        } catch (Exception e) {
             // Si el servicio lanza una excepción (ej. ResourceNotFoundException), devuelve 404
             return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new ApiResponseSuccessDto<>(
                            false,
                            "Error: No se encontró el EstadoTurno con ID: " + id,
                            null
                    ));
        }
    }
}