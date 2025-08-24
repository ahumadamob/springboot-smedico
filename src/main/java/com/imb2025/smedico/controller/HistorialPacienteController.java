package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
import com.imb2025.smedico.service.jpa.HistorialPacienteServiceImpl;

@RestController
@RequestMapping("/historialpaciente")
public class HistorialPacienteController {

    @Autowired
    private IHistorialPacienteService service;

    @GetMapping("/{id}")
    public ApiResponseSuccessDto<HistorialPaciente> getById(@PathVariable Long id) {
        HistorialPaciente historial = service.findById(id);
        return new ApiResponseSuccessDto<>(true, "Historial encontrado correctamente", historial);
    }

    @GetMapping
    public ApiResponseSuccessDto<List<HistorialPaciente>> getAll() {
        List<HistorialPaciente> list = service.findAll();
        return new ApiResponseSuccessDto<>(true, "Historiales encontrados correctamente", list);
    }

    @PostMapping
    public ApiResponseSuccessDto<HistorialPaciente> create(@RequestBody HistorialPacienteRequestDto dto) throws Exception {
        HistorialPaciente historial = service.create(service.fromDto(dto));
        return new ApiResponseSuccessDto<>(true, "Historial creado correctamente", historial);
    }

    @PutMapping("/{id}")
    public ApiResponseSuccessDto<HistorialPaciente> update(@PathVariable Long id, @RequestBody HistorialPacienteRequestDto dto) throws Exception {
        HistorialPaciente historialEntity = service.fromDto(dto);
        HistorialPaciente actualizado = service.update(id, historialEntity);
        return new ApiResponseSuccessDto<>(true, "Historial actualizado correctamente", actualizado);
    }

    @DeleteMapping("/{id}")
    public ApiResponseSuccessDto<String> delete(@PathVariable Long id) {
        service.deleteById(id);
        return new ApiResponseSuccessDto<>(true, "Historial eliminado correctamente", "Id: " + id);
    }
}

