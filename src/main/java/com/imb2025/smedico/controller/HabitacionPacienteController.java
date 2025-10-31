package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.entity.HabitacionPaciente;
import com.imb2025.smedico.service.HabitacionPacienteService;

import jakarta.validation.Valid;

// Controlador REST para la gestión de habitaciones de pacientes
@RestController
@RequestMapping("/habitaciones")
public class HabitacionPacienteController {

    @Autowired
    private HabitacionPacienteService service;


    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<HabitacionPacienteRequestDTO>>> getAll() {
        List<HabitacionPaciente> lista = service.findAll();
        List<HabitacionPacienteRequestDTO> dtos = lista.stream()
                .map(service::toDto)
                .collect(Collectors.toList());
        ApiResponseSuccessDto<List<HabitacionPacienteRequestDTO>> resp =
                new ApiResponseSuccessDto<>(true, "Lista de habitaciones obtenida correctamente", dtos);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPaciente>> getHabitacionById(@PathVariable Long id) {
        HabitacionPaciente habitacion = service.findById(id);
        ApiResponseSuccessDto<HabitacionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Habitación encontrada correctamente", habitacion);
        return ResponseEntity.ok(resp);
    }


    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPaciente>> createHabitacion(
            @Valid @RequestBody HabitacionPacienteRequestDTO dto) {

        HabitacionPaciente entity = service.fromDto(dto);
        HabitacionPaciente creada = service.save(entity);

        ApiResponseSuccessDto<HabitacionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Habitación creada correctamente", creada);

        return ResponseEntity.status(201).body(resp);
    }

  
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPaciente>> updateHabitacion(
            @PathVariable Long id,
            @Valid @RequestBody HabitacionPacienteRequestDTO dto) {

        HabitacionPaciente actualizada = service.update(id, dto);

        ApiResponseSuccessDto<HabitacionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Habitación actualizada correctamente", actualizada);

        return ResponseEntity.ok(resp);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteHabitacion(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Habitación eliminada correctamente", "ID eliminado: " + id);
        return ResponseEntity.ok(resp);
    }

    
    @GetMapping("/sector/{sector}")
    public ResponseEntity<ApiResponseSuccessDto<List<HabitacionPaciente>>> getBySector(@PathVariable String sector) {
        List<HabitacionPaciente> lista = service.findBySectorIgnoreCase(sector);
        ApiResponseSuccessDto<List<HabitacionPaciente>> resp =
                new ApiResponseSuccessDto<>(true, "Habitaciones del sector: " + sector, lista);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/count/sector/{sector}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countBySector(@PathVariable String sector) {
        Long cantidad = service.countBySectorIgnoreCase(sector);
        ApiResponseSuccessDto<Long> resp =
                new ApiResponseSuccessDto<>(true, "Cantidad de habitaciones en el sector: " + sector, cantidad);
        return ResponseEntity.ok(resp);
    }
}

