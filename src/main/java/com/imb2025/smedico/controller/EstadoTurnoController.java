package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.mapper.EstadoTurnoMapper; // NUEVO: Importamos el Mapper
import com.imb2025.smedico.dto.response.EstadoTurnoResponseDto; // NUEVO: Importamos el Response DTO
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors; 

import com.imb2025.smedico.dto.request.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.service.IEstadoTurnoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/estado-turno") 
public class EstadoTurnoController {

    private final IEstadoTurnoService estadoTurnoService; 
    private final EstadoTurnoMapper mapper; // NUEVO: Inyectamos el Mapper

    // Constructor con inyección de dependencias (Service y Mapper)
    public EstadoTurnoController(IEstadoTurnoService estadoTurnoService, EstadoTurnoMapper mapper) {
        this.estadoTurnoService = estadoTurnoService;
        this.mapper = mapper;
    }
    
    // ------------------------------------------------------------------
    // TP07: Endpoint para Filtrar (findBy...) - Devuelve Response DTO
    // ------------------------------------------------------------------

    @GetMapping(params = {"filtro", "!count"}) 
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoResponseDto>>> findByFiltro(@RequestParam(name = "filtro") String filtro) {
        List<EstadoTurno> estados = estadoTurnoService.findByNombreContaining(filtro);

        // NUEVO: Convierte las entidades filtradas a Response DTOs
        List<EstadoTurnoResponseDto> dtos = estados.stream()
                .map(mapper::toResponseDto) 
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstadoTurnoResponseDto>> response =
                new ApiResponseSuccessDto<>(true, 
                        "Listado de EstadosTurno filtrado por '" + filtro + "' obtenido correctamente.", 
                        dtos);

        return ResponseEntity.ok(response);
    }
    
    
    // TP07: Endpoint para Contar (countBy...) - No cambia el retorno (Long)
    
    @GetMapping(params = {"count", "!filtro"}) 
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByNombre(@RequestParam(name = "count") String nombre) {
        long conteo = estadoTurnoService.countByNombre(nombre);

        ApiResponseSuccessDto<Long> response =
                new ApiResponseSuccessDto<>(true, 
                        "Conteo de EstadosTurno con nombre '" + nombre + "' obtenido correctamente.", 
                        conteo);

        return ResponseEntity.ok(response);
    }

    // ------------------------------------------------------------------
    // Métodos CRUD originales - Refactorizados para usar DTOs de Respuesta
    // ------------------------------------------------------------------

    // GET de EstadoTurnoE (Obtener todos los registros por lista)
    @GetMapping(params = {"!filtro", "!count"}) 
    public ResponseEntity<ApiResponseSuccessDto<List<EstadoTurnoResponseDto>>> getAll() {
        List<EstadoTurno> estados = estadoTurnoService.findAll();

        // NUEVO: Convertimos la lista de entidades a lista de Response DTOs
        List<EstadoTurnoResponseDto> dtos = estados.stream()
                .map(mapper::toResponseDto) 
                .toList();

        ApiResponseSuccessDto<List<EstadoTurnoResponseDto>> response =
                new ApiResponseSuccessDto<>(true, "Listado de EstadosTurno obtenido correctamente", dtos);

        return ResponseEntity.ok(response);
    }

    // GET de EstadoTurnoE {id} (Obtener un registro por ID)
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoResponseDto>> getById(@PathVariable Long id) {
        EstadoTurno estadoTurno = estadoTurnoService.findById(id);

        // NUEVO: Convertimos la entidad a Response DTO
        EstadoTurnoResponseDto dto = mapper.toResponseDto(estadoTurno);
        
        ApiResponseSuccessDto<EstadoTurnoResponseDto> response = new ApiResponseSuccessDto<>(
            true,       // success
            "EstadoTurno encontrado",  // mensaje
            dto         // data
        );

        return ResponseEntity.ok(response);
    }


    // POST de EstadoTurnoE (Crear un nuevo registro)
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoResponseDto>> create(
            @Valid @RequestBody EstadoTurnoRequestDto dto) {

        // NUEVO: Conversión DTO a entidad usando el Mapper (toEntity)
        EstadoTurno entidad = mapper.toEntity(dto);

        // persistencia
        EstadoTurno creado = estadoTurnoService.create(entidad);

        // NUEVO: DTO de respuesta usando el Mapper (toResponseDto)
        EstadoTurnoResponseDto responseDto = mapper.toResponseDto(creado);

        ApiResponseSuccessDto<EstadoTurnoResponseDto> response =
                new ApiResponseSuccessDto<>(true, "EstadoTurno creado correctamente", responseDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // PUT de EstadoTurnoE (Actualizar un registro)
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<EstadoTurnoResponseDto>> update(
            @PathVariable Long id,
            @RequestBody @Valid EstadoTurnoRequestDto dto) { 

        // 1. Conversión DTO a entidad usando el Mapper
        EstadoTurno entidad = mapper.toEntity(dto);
        
        // 2. Control de Concurrencia Optimista (TP08)
        // El DTO debe traer la versión actual y el ID.
        
        // Asignamos el ID (del PathVariable) a la entidad
        entidad.setId(id);
        
        // Asignamos la versión del DTO a la entidad para que JPA la chequee en el update
        if (dto.getVersion() != null) {
            entidad.setVersion(dto.getVersion());
        }

        // 3. Persistencia
        EstadoTurno actualizado = estadoTurnoService.update(id, entidad);

        // 4. NUEVO: DTO de respuesta usando el Mapper (incluye la nueva Version)
        EstadoTurnoResponseDto responseDto = mapper.toResponseDto(actualizado);

        ApiResponseSuccessDto<EstadoTurnoResponseDto> response =
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