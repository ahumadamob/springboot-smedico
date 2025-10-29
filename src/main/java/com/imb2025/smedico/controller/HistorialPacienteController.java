package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.HistorialPacienteRequestDto;
import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.service.IHistorialPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/historialpaciente")
public class HistorialPacienteController {

    @Autowired
    private IHistorialPacienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HistorialPaciente>> getById(@PathVariable Long id) {
        HistorialPaciente historial = service.findById(id);
        ApiResponseSuccessDto<HistorialPaciente> resp =
            new ApiResponseSuccessDto<>(true, "Historial encontrado correctamente", historial);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<HistorialPaciente>>> getAll() {
        List<HistorialPaciente> list = service.findAll();
        ApiResponseSuccessDto<List<HistorialPaciente>> resp =
            new ApiResponseSuccessDto<>(true, "Historiales encontrados correctamente", list);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<HistorialPaciente>> create(
            @Valid @RequestBody HistorialPacienteRequestDto dto) {
        HistorialPaciente historial = service.create(service.fromDto(dto));
        ApiResponseSuccessDto<HistorialPaciente> resp =
            new ApiResponseSuccessDto<>(true, "Historial creado correctamente", historial);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HistorialPaciente>> update(
            @PathVariable Long id,
            @Valid @RequestBody HistorialPacienteRequestDto dto) {
        HistorialPaciente historialEntity = service.fromDto(dto);
        HistorialPaciente actualizado = service.update(id, historialEntity);
        ApiResponseSuccessDto<HistorialPaciente> resp =
            new ApiResponseSuccessDto<>(true, "Historial actualizado correctamente", actualizado);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp =
            new ApiResponseSuccessDto<>(true, "Historial eliminado correctamente", "Id: " + id);
        return ResponseEntity.ok(resp);
    }
 // Buscar historiales por evento
    @GetMapping("/buscar/evento/{evento}")
    public ResponseEntity<ApiResponseSuccessDto<List<HistorialPaciente>>> getByEvento(@PathVariable String evento) {
        List<HistorialPaciente> lista = service.findByEvento(evento);
        ApiResponseSuccessDto<List<HistorialPaciente>> resp =
            new ApiResponseSuccessDto<>(true, "Historiales encontrados por evento", lista);
        return ResponseEntity.ok(resp);
    }

    // Contar historiales por fecha
    @GetMapping("/contar/fecha/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByFecha(@PathVariable String fecha) {
        java.time.LocalDate date = java.time.LocalDate.parse(fecha); // formato YYYY-MM-DD
        Long cantidad = service.countByFecha(date);
        ApiResponseSuccessDto<Long> resp =
            new ApiResponseSuccessDto<>(true, "Cantidad de historiales en esa fecha", cantidad);
        return ResponseEntity.ok(resp);
    }

}

