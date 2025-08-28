package com.imb2025.smedico.controller;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.HorarioAtencionRequestDto;
import com.imb2025.smedico.dto.HorarioAtencionResponseDto; 
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

    private HorarioAtencion convertToEntity(HorarioAtencionRequestDto requestDto) {
        HorarioAtencion horarioEntity = new HorarioAtencion();

        if (requestDto.getMedicoId() != null) {
            Medico medico = medicoService.findById(requestDto.getMedicoId());
            if (medico == null) {
                throw new ResourceNotFoundException("Médico no encontrado con ID: " + requestDto.getMedicoId());
            }
            horarioEntity.setMedico(medico);
        }

        horarioEntity.setDiaSemana(requestDto.getDiaSemana());
        horarioEntity.setHoraInicio(requestDto.getHoraInicio());
        horarioEntity.setHoraFin(requestDto.getHoraFin());
        return horarioEntity;
    }
    
    private HorarioAtencionResponseDto convertToResponseDto(HorarioAtencion horarioEntity) {
        HorarioAtencionResponseDto responseDto = new HorarioAtencionResponseDto();
        responseDto.setId(horarioEntity.getId());

        if (horarioEntity.getMedico() != null) {
            responseDto.setMedicoId(horarioEntity.getMedico().getId());
        }

        responseDto.setDiaSemana(horarioEntity.getDiaSemana());
        responseDto.setHoraInicio(horarioEntity.getHoraInicio());
        responseDto.setHoraFin(horarioEntity.getHoraFin());
        return responseDto;
    }

    @GetMapping
    public ResponseEntity<List<HorarioAtencionResponseDto>> getAllHorarioAtencion() {
        List<HorarioAtencionResponseDto> horarios = horarioAtencionService.findAll().stream()
                .map(this::convertToResponseDto)
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
    public ResponseEntity<ApiResponseSuccessDto<HorarioAtencion>> createHorarioAtencion(@RequestBody HorarioAtencionRequestDto requestDto) throws Exception {
        HorarioAtencion horarioEntity = convertToEntity(requestDto);
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
    public ResponseEntity<ApiResponseSuccessDto<HorarioAtencion>> updateHorarioAtencion(@PathVariable Long id, @RequestBody HorarioAtencionRequestDto requestDto) throws Exception {
        if (!horarioAtencionService.existsById(id)) {
            ApiResponseSuccessDto<HorarioAtencion> response = new ApiResponseSuccessDto<>(
                false,
                "Horario no encontrado con ID: " + id,
                null
            );
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        HorarioAtencion horarioEntity = convertToEntity(requestDto);
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
