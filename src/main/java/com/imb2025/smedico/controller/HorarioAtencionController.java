package com.imb2025.smedico.controller;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;

import java.util.List;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.HorarioAtencionRequestDto;
import com.imb2025.smedico.dto.HorarioAtencionResponseDTO;
import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.entity.Medico;
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

    private HorarioAtencion convertToEntity(HorarioAtencionRequestDto requestDTO) {
        HorarioAtencion horarioEntity = new HorarioAtencion();

        if (requestDTO.getMedicoId() != null) {
            Medico medico = medicoService.findById(requestDTO.getMedicoId());
            if (medico == null) {
                throw new ResourceNotFoundException("Médico no encontrado con ID: " + requestDTO.getMedicoId());
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
        List<HorarioAtencionResponseDTO> horarios = horarioAtencionService.findAll().stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());

        if (horarios.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(horarios);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HorarioAtencion>> getHorarioAtencionById(@PathVariable Long id) {
        HorarioAtencion horario = horarioAtencionService.findById(id);

        ApiResponseSuccessDto<HorarioAtencion> response = new ApiResponseSuccessDto<>(
        		true,
        	    "Horario encontrado exitosamente", 
        	    horario 
  );

        return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<HorarioAtencion>> createHorarioAtencion(@RequestBody HorarioAtencionRequestDto requestDTO) throws Exception {
        HorarioAtencion horarioEntity = convertToEntity(requestDTO);
        horarioEntity.setId(null);
        HorarioAtencion savedHorario = horarioAtencionService.create(horarioEntity);

        ApiResponseSuccessDto<HorarioAtencion> response = new ApiResponseSuccessDto<>(
            true,
            "Horario creado exitosamente",
            savedHorario
        );

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HorarioAtencion>> updateHorarioAtencion(@PathVariable Long id, @RequestBody HorarioAtencionRequestDto requestDTO) throws Exception {
        if (!horarioAtencionService.existsById(id)) {
            ApiResponseSuccessDto<HorarioAtencion> response = new ApiResponseSuccessDto<>(
                false,
                "Horario no encontrado con ID: " + id,
                null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        HorarioAtencion horarioEntity = convertToEntity(requestDTO);
        HorarioAtencion updatedHorario = horarioAtencionService.update(id, horarioEntity);

        ApiResponseSuccessDto<HorarioAtencion> response = new ApiResponseSuccessDto<>(
            true,
            "Horario actualizado exitosamente",
            updatedHorario
        );
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteHorarioAtencion(@PathVariable Long id) {
        try {
            horarioAtencionService.deleteById(id);
            ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>(
                true,
                "Horario de atención " + id + " eliminado correctamente.",
                null
            );
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>(
                false,
                e.getMessage(),
                null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }
    }


   
    
}
