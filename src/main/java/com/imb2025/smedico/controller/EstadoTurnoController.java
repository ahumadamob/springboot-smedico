package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors; // Necesario para el .collect(Collectors.toList())

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.service.IEstadoTurnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estado-turno") // Los endpoints definidos comenzarán con "/estado-turno"
public class EstadoTurnoController {

    private final IEstadoTurnoService estadoTurnoService; // Instanciamos EstadoTurnoS, para la lógica

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
    @GetMapping(params = {"filtro", "!count"}) // Mapeo: Si está 'filtro' y NO 'count'
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> findByFiltro(@RequestParam(name = "filtro") String filtro) {
        // Llama al nuevo método del servicio
        List<EstadoTurno> estados = estadoTurnoService.findByNombreContaining(filtro);

        // Convierte las entidades filtradas a DTOs
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                // Asegúrate de usar el constructor que acepta ID y Nombre
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre())) 
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstadoTurnoRequestDto>> response =
                new ApiResponseSuccessDto<>(true, 
                        "Listado de EstadosTurno filtrado por '" + filtro + "' obtenido correctamente.", 
                        dtos);

        return ResponseEntity.ok(response);
    }
    
    
    // TP07: Endpoint para Contar (countBy...)
    // URL de ejemplo: GET /estado-turno?count=Pendiente
    
    /**
      Endpoint para obtener el conteo de EstadosTurno con un nombre específico.
      Se activa cuando la URL incluye el parámetro 'count' y NO 'filtro'.
      @param nombre Nombre exacto a contar (case-insensitive).
      @return Respuesta estándar con el número de coincidencias.
     */
    @GetMapping(params = {"count", "!filtro"}) // Mapeo: Si está 'count' y NO 'filtro'
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByNombre(@RequestParam(name = "count") String nombre) {
        // Llama al nuevo método del servicio
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
    // CRUCIAL: Ahora usa el mapeo `params = {"!filtro", "!count"}`
    @GetMapping(params = {"!filtro", "!count"}) 
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoRequestDto>>> getAll() {
        List<EstadoTurno> estados = estadoTurnoService.findAll();

        // Convertimos la lista de entidades a lista de DTOs de respuesta
        List<EstadoTurnoRequestDto> dtos = estados.stream()
                // Se usa el constructor con ID para las respuestas GET
                .map(e -> new EstadoTurnoRequestDto(e.getId(), e.getNombre())) 
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
        // Usamos el constructor con ID y Nombre
        EstadoTurnoRequestDto dto = new EstadoTurnoRequestDto(estadoTurno.getId(), estadoTurno.getNombre());
        
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
        // Asegúrate de incluir el ID generado en la respuesta
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(creado.getId(), creado.getNombre());

        // respuesta estándar
        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno creado correctamente", responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoRequestDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid EstadoTurnoRequestDto dto) { // Agregamos @Valid aquí

        EstadoTurno estadoTurno = estadoTurnoService.fromDto(dto);
        EstadoTurno actualizado = estadoTurnoService.update(id, estadoTurno);

        // Reutilizamos el mismo DTO para la respuesta
        EstadoTurnoRequestDto responseDto = new EstadoTurnoRequestDto(
                actualizado.getId(), // Asegúrate de devolver el ID
                actualizado.getNombre()
        );

        ApiResponseSuccessDto<EstadoTurnoRequestDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno actualizado correctamente", responseDto);

        return ResponseEntity.ok(response);
    }


    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        // Usamos try-catch para manejar la excepción de no encontrado que lanza el servicio
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
