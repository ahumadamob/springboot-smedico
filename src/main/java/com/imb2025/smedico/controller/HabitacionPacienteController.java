package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.entity.HabitacionPaciente;
import com.imb2025.smedico.service.HabitacionPacienteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionPacienteController {

    @Autowired
    private HabitacionPacienteService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<HabitacionPaciente>>> getAll() {
        List<HabitacionPaciente> habitaciones = service.findAll();
        ApiResponseSuccessDto<List<HabitacionPaciente>> resp =
                new ApiResponseSuccessDto<>(true, "Lista de habitaciones obtenida correctamente", habitaciones);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPaciente>> getById(@PathVariable Long id) {
        HabitacionPaciente habitacion = service.findById(id);
        ApiResponseSuccessDto<HabitacionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Habitación encontrada correctamente", habitacion);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPaciente>> create(@Valid @RequestBody HabitacionPacienteRequestDTO dto) {
        HabitacionPaciente habitacion = service.fromDto(dto);
        HabitacionPaciente guardada = service.save(habitacion);
        ApiResponseSuccessDto<HabitacionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Habitación creada correctamente", guardada);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HabitacionPaciente>> update(@PathVariable Long id, @Valid @RequestBody HabitacionPacienteRequestDTO dto) {
        HabitacionPaciente actualizada = service.update(id, dto);
        ApiResponseSuccessDto<HabitacionPaciente> resp =
                new ApiResponseSuccessDto<>(true, "Habitación actualizada correctamente", actualizada);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Habitación eliminada correctamente", "ID: " + id);
        return ResponseEntity.ok(resp);
    }
}
