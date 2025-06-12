package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // Importación necesaria para ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imb2025.smedico.dto.HorarioAtencionRequestDTO;
import com.imb2025.smedico.dto.HorarioAtencionResponseDTO;
import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.service.IHorarioAtencionService;
import com.imb2025.smedico.service.IMedicoService;

@RestController
@RequestMapping("/horarioAtencion")
public class HorarioAtencionController {

    @Autowired
    private IHorarioAtencionService horarioAtencionService;

    @Autowired
    private IMedicoService medicoService;

    private HorarioAtencion convertToEntity(HorarioAtencionRequestDTO requestDTO) {
        HorarioAtencion horarioEntity = new HorarioAtencion();

        if (requestDTO.getMedicoId() != null) {
            Medico medico = medicoService.findById(requestDTO.getMedicoId());
            if (medico == null) {
                throw new IllegalArgumentException("Médico no encontrado con ID: " + requestDTO.getMedicoId());
            }
            horarioEntity.setMedico(medico);
        } else {
            horarioEntity.setMedico(null); // Establecer médico como null si no se proporciona ID
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
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } else {
            return ResponseEntity.ok(horarios); // HTTP 200 OK
        }
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Object> getHorarioAtencionById(@PathVariable Long id) {
        HorarioAtencion horario = horarioAtencionService.getHorarioAtencionById(id);
        if (horario == null) {
            return ResponseEntity.noContent().build(); // HTTP 204 No Content
        } else {
            return ResponseEntity.ok(convertToResponseDTO(horario)); // HTTP 200 OK
        }
    }

    
    @PostMapping
    public ResponseEntity<Object> createHorarioAtencion(@RequestBody HorarioAtencionRequestDTO requestDTO) {
        HorarioAtencion horarioEntity = convertToEntity(requestDTO);
        horarioEntity.setId(null); // Asegura que se guarde como una nueva entidad, no como una actualización accidental

        HorarioAtencion savedHorario = horarioAtencionService.save(horarioEntity);
        return ResponseEntity.ok(convertToResponseDTO(savedHorario)); // HTTP 200 OK
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateHorarioAtencion(@PathVariable Long id, @RequestBody HorarioAtencionRequestDTO requestDTO) {
        HorarioAtencion horarioEntity = convertToEntity(requestDTO);
        horarioEntity.setId(id); 

        HorarioAtencion updatedHorario = horarioAtencionService.save(horarioEntity);
        return ResponseEntity.ok(convertToResponseDTO(updatedHorario)); // HTTP 200 OK
    }

    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteHorarioAtencion(@PathVariable Long id) {
       
        horarioAtencionService.deleteHorarioAtencion(id);
        return ResponseEntity.ok("Horario de atención " + id.toString() + " eliminado correctamente."); // HTTP 200 OK
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> HandlerGlobalException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage()); // HTTP 400 Bad Request
    }
}