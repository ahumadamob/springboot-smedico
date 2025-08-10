package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

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

import com.imb2025.smedico.dto.HorarioAtencionRequestDto;
import com.imb2025.smedico.dto.HorarioAtencionResponseDTO; // Importación correcta del DTO de respuesta
import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.exception.RecursoNoEncontradoException;
import com.imb2025.smedico.service.IHorarioAtencionService;
import com.imb2025.smedico.service.IMedicoService;

@RestController
@RequestMapping("/horarioAtencion")
public class HorarioAtencionController {

    @Autowired
    private IHorarioAtencionService horarioAtencionService;

    @Autowired
    private IMedicoService medicoService;

    private HorarioAtencion convertToEntity(HorarioAtencionRequestDto requestDTO) {
        HorarioAtencion horarioEntity = new HorarioAtencion();

        if (requestDTO.getMedicoId() != null) {
            Medico medico = medicoService.findById(requestDTO.getMedicoId());
            if (medico == null) {
                throw new RecursoNoEncontradoException("Médico no encontrado con ID: " + requestDTO.getMedicoId());
            }
            horarioEntity.setMedico(medico);
        } else {
            horarioEntity.setMedico(null);
        }

        horarioEntity.setDiaSemana(requestDTO.getDiaSemana());
        horarioEntity.setHoraInicio(requestDTO.getHoraInicio());
        horarioEntity.setHoraFin(requestDTO.getHoraFin());
        return horarioEntity;
    }

    private HorarioAtencionResponseDTO convertToResponseDTO(HorarioAtencion horarioEntity) {
        HorarioAtencionResponseDTO responseDTO = new HorarioAtencionResponseDTO();
        responseDTO.setId(horarioEntity.getId());

        if (horarioEntity.getMedico() != null) {
            responseDTO.setMedicoId(horarioEntity.getMedico().getId());
        } else {
            responseDTO.setMedicoId(null);
        }

        responseDTO.setDiaSemana(horarioEntity.getDiaSemana());
        responseDTO.setHoraInicio(horarioEntity.getHoraInicio());
        responseDTO.setHoraFin(horarioEntity.getHoraFin());
        return responseDTO;
    }

    @GetMapping
    public ResponseEntity<List<HorarioAtencionResponseDTO>> getAllHorarioAtencion() {
        List<HorarioAtencionResponseDTO> horarios = horarioAtencionService.getAllHorarioAtencion().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getHorarioAtencionById(@PathVariable Long id) {
        HorarioAtencion horario = horarioAtencionService.getHorarioAtencionById(id);
        if (horario == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario de atención no encontrado con ID: " + id);
        } else {
            return ResponseEntity.ok(convertToResponseDTO(horario));
        }
    }

    @PostMapping
    public ResponseEntity<Object> createHorarioAtencion(@RequestBody HorarioAtencionRequestDto requestDTO) {
        HorarioAtencion horarioEntity = convertToEntity(requestDTO);
        horarioEntity.setId(null);
        HorarioAtencion savedHorario = horarioAtencionService.save(horarioEntity);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToResponseDTO(savedHorario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHorarioAtencion(@PathVariable Long id, @RequestBody HorarioAtencionRequestDto requestDTO) {
        if (!horarioAtencionService.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Horario no encontrado con ID: " + id);
        }

        HorarioAtencion horarioEntity = convertToEntity(requestDTO);
        horarioEntity.setId(id);
        HorarioAtencion updatedHorario = horarioAtencionService.save(horarioEntity);
        return ResponseEntity.ok(convertToResponseDTO(updatedHorario));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHorarioAtencion(@PathVariable Long id) {
        try {
            horarioAtencionService.deleteHorarioAtencion(id);
            return ResponseEntity.ok("Horario de atención " + id + " eliminado correctamente.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        }
    }

    @ExceptionHandler(RecursoNoEncontradoException.class)
    public ResponseEntity<String> handleNotFoundException(RecursoNoEncontradoException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }
}
