package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.request.HorarioAtencionRequestDto;
import com.imb2025.smedico.dto.response.HorarioAtencionResponseDto;
import com.imb2025.smedico.mapper.HorarioAtencionMapper;
import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.service.IHorarioAtencionService;
import com.imb2025.smedico.service.IMedicoService;

@RestController
@RequestMapping("/horarioAtencion")
public class HorarioAtencionController {

    @Autowired
    private IHorarioAtencionService horarioAtencionService;

    @Autowired
    private IMedicoService medicoService;

    @Autowired
    private HorarioAtencionMapper mapper;

    @GetMapping
    public ResponseEntity<List<HorarioAtencionResponseDto>> getAllHorarioAtencion() {
        List<HorarioAtencion> horariosEntities = horarioAtencionService.findAll();
        List<HorarioAtencionResponseDto> horarios = horariosEntities.stream()
                .map(mapper::toResponseDto)
                .collect(Collectors.toList());

        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<HorarioAtencionResponseDto> getHorarioAtencionById(@PathVariable Long id) {
        HorarioAtencion horario = horarioAtencionService.findById(id);
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } else {
            return ResponseEntity.ok(mapper.toResponseDto(horario));
        }
    }

    @PostMapping
    public ResponseEntity<HorarioAtencionResponseDto> createHorarioAtencion(@RequestBody HorarioAtencionRequestDto requestDTO) throws Exception {
        HorarioAtencion horarioEntity = mapper.fromDto(requestDTO);
        horarioEntity.setId(null);
        HorarioAtencion savedHorario = horarioAtencionService.create(horarioEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toResponseDto(savedHorario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<HorarioAtencionResponseDto> updateHorarioAtencion(@PathVariable Long id, @RequestBody HorarioAtencionRequestDto requestDTO) throws Exception {
        if (!horarioAtencionService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        HorarioAtencion horarioEntity = mapper.fromDto(requestDTO);
        HorarioAtencion updatedHorario = horarioAtencionService.update(id, horarioEntity);
        return ResponseEntity.ok(mapper.toResponseDto(updatedHorario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHorarioAtencion(@PathVariable Long id) {
        try {
            horarioAtencionService.deleteById(id);
            return ResponseEntity.ok("Horario de atención " + id + " eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
}
