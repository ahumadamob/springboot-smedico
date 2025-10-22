package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.HistorialPacienteRequestDto;
import com.imb2025.smedico.dto.response.HistorialPacienteResponseDto;
import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.dto.mapper.HistorialPacienteMapper;
import com.imb2025.smedico.service.IHistorialPacienteService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/historialpaciente")
public class HistorialPacienteController {

    @Autowired
    private IHistorialPacienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HistorialPacienteResponseDto>> getById(@PathVariable Long id) {
        HistorialPaciente historial = service.findById(id);
        HistorialPacienteResponseDto dto = HistorialPacienteMapper.toResponseDto(historial);
        ApiResponseSuccessDto<HistorialPacienteResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Historial encontrado correctamente", dto);
        return ResponseEntity.ok(resp);
    }

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<HistorialPacienteResponseDto>>> getAll() {
        List<HistorialPacienteResponseDto> list =
                HistorialPacienteMapper.toResponseDtoList(service.findAll());
        ApiResponseSuccessDto<List<HistorialPacienteResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, "Historiales encontrados correctamente", list);
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<HistorialPacienteResponseDto>> create(
            @Valid @RequestBody HistorialPacienteRequestDto dto) {

        HistorialPaciente historial = service.create(dto);
        HistorialPacienteResponseDto responseDto = HistorialPacienteMapper.toResponseDto(historial);

        ApiResponseSuccessDto<HistorialPacienteResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Historial creado correctamente", responseDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HistorialPacienteResponseDto>> update(
            @PathVariable Long id,
            @Valid @RequestBody HistorialPacienteRequestDto dto) {

        HistorialPaciente actualizado = service.update(id, dto);
        HistorialPacienteResponseDto responseDto = HistorialPacienteMapper.toResponseDto(actualizado);

        ApiResponseSuccessDto<HistorialPacienteResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Historial actualizado correctamente", responseDto);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Historial eliminado correctamente", "Id: " + id);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/buscar/evento/{evento}")
    public ResponseEntity<ApiResponseSuccessDto<List<HistorialPacienteResponseDto>>> getByEvento(@PathVariable String evento) {
        List<HistorialPacienteResponseDto> lista =
                HistorialPacienteMapper.toResponseDtoList(service.findByEvento(evento));
        ApiResponseSuccessDto<List<HistorialPacienteResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, "Historiales encontrados por evento", lista);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/contar/fecha/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByFecha(@PathVariable String fecha) {
        java.time.LocalDate date = java.time.LocalDate.parse(fecha);
        Long cantidad = service.countByFecha(date);
        ApiResponseSuccessDto<Long> resp =
                new ApiResponseSuccessDto<>(true, "Cantidad de historiales en esa fecha", cantidad);
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("recurso/vigentes")
    public ResponseEntity<ApiResponseSuccessDto<List<HistorialPacienteResponseDto>>> getVigentes() {
        List<HistorialPacienteResponseDto> lista =
                HistorialPacienteMapper.toResponseDtoList(service.findVigentes(java.time.LocalDate.now()));
        ApiResponseSuccessDto<List<HistorialPacienteResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, "Historiales vigentes", lista);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("recurso/vencidos")
    public ResponseEntity<ApiResponseSuccessDto<List<HistorialPacienteResponseDto>>> getVencidos() {
        List<HistorialPacienteResponseDto> lista =
                HistorialPacienteMapper.toResponseDtoList(service.findVencidos(java.time.LocalDate.now()));
        ApiResponseSuccessDto<List<HistorialPacienteResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, "Historiales vencidos", lista);
        return ResponseEntity.ok(resp);
    }


}
