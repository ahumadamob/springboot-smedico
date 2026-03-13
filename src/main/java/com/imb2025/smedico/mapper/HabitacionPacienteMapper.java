package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.dto.response.HabitacionPacienteResponseDto;
import com.imb2025.smedico.entity.HabitacionPaciente;

public class HabitacionPacienteMapper {

    // Convierte un DTO de request a entidad
    public static HabitacionPaciente fromDto(HabitacionPacienteRequestDTO dto) {

        HabitacionPaciente entidad = new HabitacionPaciente();

        entidad.setNumeroHabitacion(dto.getNumeroHabitacion());
        entidad.setPiso(dto.getPiso());
        entidad.setSector(dto.getSector());
        entidad.setCamasDisponibles(dto.getCamasDisponibles());
        entidad.setDescripcion(dto.getDescripcion());

        entidad.setIdentificadorLegible(dto.getIdentificadorLegible());
        entidad.setFechaVigencia(dto.getFechaVigencia());
        entidad.setObservacionInterna(dto.getObservacionInterna());

        return entidad;
    }

    // Convierte una entidad a DTO de respuesta
    public static HabitacionPacienteResponseDto toResponseDto(HabitacionPaciente entidad) {

        HabitacionPacienteResponseDto dto = new HabitacionPacienteResponseDto();
        dto.setId(entidad.getId());
        dto.setNumeroHabitacion(entidad.getNumeroHabitacion());
        dto.setPiso(entidad.getPiso());
        dto.setSector(entidad.getSector());
        dto.setCamasDisponibles(entidad.getCamasDisponibles());
        dto.setDescripcion(entidad.getDescripcion());
        dto.setIdentificadorLegible(entidad.getIdentificadorLegible());
        dto.setFechaVigencia(entidad.getFechaVigencia());
        
        dto.setObservacionInterna(entidad.getObservacionInterna());

        return dto;
    }
}
