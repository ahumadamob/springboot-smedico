package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.dto.response.HabitacionPacienteResponseDto;
import com.imb2025.smedico.entity.HabitacionPaciente;
import com.imb2025.smedico.mapper.HabitacionPacienteMapper;
import com.imb2025.smedico.service.HabitacionPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/habitaciones")
public class HabitacionPacienteController {

    private final HabitacionPacienteService service;

    public HabitacionPacienteController(HabitacionPacienteService service) {
        this.service = service;
    }

    /** 🔹¿Helper para respuestas estándar */
    private <T> ResponseEntity<ApiResponseSuccessDto<T>> buildResponse(String message, T data, HttpStatus status) {
        ApiResponseSuccessDto<T> resp = new ApiResponseSuccessDto<>(true, message, data);
        return ResponseEntity.status(status).body(resp);
    }

    /**  Obtener todas las habitaciones */
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<HabitacionPacienteResponseDto>>> getAll() {
        List<HabitacionPaciente> entidades = service.findAll();
        List<HabitacionPacienteResponseDto> lista = entidades.stream()
                .map(HabitacionPacienteMapper::toResponseDto)
                .collect(Collectors.toList());
        return buildResponse("Listado de habitaciones obtenido correctamente", lista, HttpStatus.OK);
    }

    /**  Obtener una habitación por ID */
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPacienteResponseDto>> getHabitacionById(@PathVariable Long id) {
        HabitacionPaciente entidad = service.findById(id);
        HabitacionPacienteResponseDto dto = HabitacionPacienteMapper.toResponseDto(entidad);
        return buildResponse("Habitación encontrada correctamente", dto, HttpStatus.OK);
    }

    /**  Crear una habitación */
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPacienteResponseDto>> createHabitacion(
            @Valid @RequestBody HabitacionPacienteRequestDTO dto) {

        HabitacionPaciente entidad = HabitacionPacienteMapper.fromDto(dto);
        HabitacionPaciente creada = service.save(entidad);
        HabitacionPacienteResponseDto response = HabitacionPacienteMapper.toResponseDto(creada);

        return buildResponse("Habitación creada correctamente", response, HttpStatus.CREATED);
    }

    /**  Actualizar una habitación */
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPacienteResponseDto>> updateHabitacion(
            @PathVariable Long id,
            @Valid @RequestBody HabitacionPacienteRequestDTO dto) {

        HabitacionPaciente entidad = HabitacionPacienteMapper.fromDto(dto);
        entidad.setId(id);
        HabitacionPaciente actualizada = service.update(id, dto);
        HabitacionPacienteResponseDto response = HabitacionPacienteMapper.toResponseDto(actualizada);

        return buildResponse("Habitación actualizada correctamente", response, HttpStatus.OK);
    }

    /**  Eliminar una habitación */
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteHabitacion(@PathVariable Long id) {
        service.deleteById(id);
        return buildResponse("Habitación eliminada correctamente", "ID eliminado: " + id, HttpStatus.OK);
    }

    /**  Buscar por sector */
    @GetMapping("/sector/{sector}")
    public ResponseEntity<ApiResponseSuccessDto<List<HabitacionPacienteResponseDto>>> getBySector(@PathVariable String sector) {
        List<HabitacionPaciente> entidades = service.findBySectorIgnoreCase(sector);
        List<HabitacionPacienteResponseDto> lista = entidades.stream()
                .map(HabitacionPacienteMapper::toResponseDto)
                .collect(Collectors.toList());
        return buildResponse("Habitaciones del sector: " + sector, lista, HttpStatus.OK);
    }

    /** 🔹 Contar habitaciones por sector */
    @GetMapping("/count/sector/{sector}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countBySector(@PathVariable String sector) {
        Long cantidad = service.countBySectorIgnoreCase(sector);
        return buildResponse("Cantidad de habitaciones en el sector: " + sector, cantidad, HttpStatus.OK);
    }
}

