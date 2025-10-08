package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.service.IConsultaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private IConsultaService consultaService;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<ConsultaRequestDto>>> findAll() {
        List<Consulta> lista = consultaService.findAll();

        List<ConsultaRequestDto> data = lista.stream()
            .map(c -> {
                ConsultaRequestDto dto = new ConsultaRequestDto();
                dto.setFecha(c.getFecha());
                dto.setTurnoId(c.getTurno() != null ? c.getTurno().getId() : null);
                dto.setDuracionMin(c.getDuracionMin());
                dto.setComentarios(c.getComentarios());
                return dto;
            })
            .collect(Collectors.toList());

        ApiResponseSuccessDto<List<ConsultaRequestDto>> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage(data.isEmpty() ? "No hay consultas" : "Listado de consultas");
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultaRequestDto>> getById(@PathVariable Long id) {
        Consulta c = consultaService.findById(id);

        ConsultaRequestDto data = new ConsultaRequestDto();
        data.setFecha(c.getFecha());
        data.setTurnoId(c.getTurno() != null ? c.getTurno().getId() : null);
        data.setDuracionMin(c.getDuracionMin());
        data.setComentarios(c.getComentarios());

        ApiResponseSuccessDto<ConsultaRequestDto> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage("Consulta encontrada");
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<ConsultaRequestDto>> create(@Valid @RequestBody ConsultaRequestDto dto) {
        Consulta creada = consultaService.createFromDto(dto);

        ConsultaRequestDto data = new ConsultaRequestDto();
        data.setFecha(creada.getFecha());
        data.setTurnoId(creada.getTurno() != null ? creada.getTurno().getId() : null);
        data.setDuracionMin(creada.getDuracionMin());
        data.setComentarios(creada.getComentarios());

        ApiResponseSuccessDto<ConsultaRequestDto> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage("Consulta creada");
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultaRequestDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody ConsultaRequestDto dto) {

        Consulta actualizada = consultaService.updateFromDto(id, dto);

        ConsultaRequestDto data = new ConsultaRequestDto();
        data.setFecha(actualizada.getFecha());
        data.setTurnoId(actualizada.getTurno() != null ? actualizada.getTurno().getId() : null);
        data.setDuracionMin(actualizada.getDuracionMin());
        data.setComentarios(actualizada.getComentarios());

        ApiResponseSuccessDto<ConsultaRequestDto> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(data);
        resp.setMessage("Consulta actualizada");
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        consultaService.deleteById(id);

        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(null);
        resp.setMessage("Consulta eliminada");
        return ResponseEntity.ok(resp); // 200 con wrapper (política del curso)
    }
}
