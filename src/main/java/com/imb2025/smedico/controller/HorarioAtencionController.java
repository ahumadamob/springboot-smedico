package com.imb2025.smedico.controller;
import com.imb2025.smedico.dto.ApiResponseErrorDto;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid; 

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

    @Operation(summary = "Obtiene todos los horarios de atención")
    @ApiResponse(responseCode = "200", description = "Horarios obtenidos exitosamente")
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<HorarioAtencionResponseDto>>> getAllHorarioAtencion() {
        List<HorarioAtencionResponseDto> horarios = horarioAtencionService.findAll().stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<HorarioAtencionResponseDto>> response = new ApiResponseSuccessDto<>(
            true,
            "Horarios obtenidos exitosamente",
            horarios
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Obtiene un horario de atención por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horario encontrado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Horario no encontrado", 
                     content = @Content(schema = @Schema(implementation = ApiResponseErrorDto.class)))
    })
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

    @Operation(summary = "Crea un nuevo horario de atención")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Horario creado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", 
                     content = @Content(schema = @Schema(implementation = ApiResponseErrorDto.class)))
    })
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<HorarioAtencion>> createHorarioAtencion(@Valid @RequestBody HorarioAtencionRequestDto requestDto) {
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
    
    @Operation(summary = "Actualiza un horario de atención existente")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horario actualizado exitosamente"),
        @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos", 
                     content = @Content(schema = @Schema(implementation = ApiResponseErrorDto.class))),
        @ApiResponse(responseCode = "404", description = "Horario no encontrado", 
                     content = @Content(schema = @Schema(implementation = ApiResponseErrorDto.class)))
    })
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<HorarioAtencion>> updateHorarioAtencion(@PathVariable Long id, @Valid @RequestBody HorarioAtencionRequestDto requestDto) {
        HorarioAtencion horarioEntity = convertToEntity(requestDto);
        HorarioAtencion updatedHorario = horarioAtencionService.update(id, horarioEntity);

        ApiResponseSuccessDto<HorarioAtencion> response = new ApiResponseSuccessDto<>(
            true,
            "Horario actualizado exitosamente",
            updatedHorario
        );
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Elimina un horario de atención por su ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Horario eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Horario no encontrado", 
                     content = @Content(schema = @Schema(implementation = ApiResponseErrorDto.class)))
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> deleteHorarioAtencion(@PathVariable Long id) {
        horarioAtencionService.deleteById(id);
        ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>(
            true,
            "Horario de atención " + id + " eliminado correctamente.",
            null
        );
        return ResponseEntity.ok(response);
    }
}