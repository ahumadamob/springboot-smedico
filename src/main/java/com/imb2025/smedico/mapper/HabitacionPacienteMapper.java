package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.HabitacionPacienteRequestDTO;
import com.imb2025.smedico.dto.response.HabitacionPacienteResponseDto;
import com.imb2025.smedico.entity.HabitacionPaciente;

public class HabitacionPacienteMapper {

    //  Convierte un DTO de request a entidad
    public static HabitacionPaciente fromDto(HabitacionPacienteRequestDTO dto) {
        HabitacionPaciente entidad = new HabitacionPaciente();
        entidad.setNumeroHabitacion(dto.getNumeroHabitacion());
        entidad.setPiso(dto.getPiso());
        entidad.setSector(dto.getSector());
        entidad.setCamasDisponibles(dto.getCamasDisponibles());
        entidad.setDescripcion(dto.getDescripcion());
        return entidad;
    }

    //  Convierte una entidad a DTO de respuesta
    public static HabitacionPacienteResponseDto toResponseDto(HabitacionPaciente entidad) {
        if (entidad == null) {
            return null;
        }

        return new HabitacionPacienteResponseDto(
            entidad.getId(),
            entidad.getNumeroHabitacion(),
            entidad.getPiso(),
            entidad.getSector(),
            entidad.getCamasDisponibles(),
            entidad.getDescripcion(),
            entidad.getVersion() != null ? entidad.getVersion().intValue() : null
        );
    }
}
