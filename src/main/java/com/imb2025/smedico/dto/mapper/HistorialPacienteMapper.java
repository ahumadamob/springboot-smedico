package com.imb2025.smedico.dto.mapper;

import com.imb2025.smedico.dto.request.HistorialPacienteRequestDto;
import com.imb2025.smedico.dto.response.HistorialPacienteResponseDto;
import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.entity.Paciente;

import java.util.List;
import java.util.stream.Collectors;

public class HistorialPacienteMapper {

	public static HistorialPaciente fromDto(HistorialPacienteRequestDto dto, Paciente paciente) {
        HistorialPaciente entidad = new HistorialPaciente();
        entidad.setEvento(dto.getEvento());
        entidad.setFecha(dto.getFecha());
        entidad.setObservacion(dto.getObservacion());
        entidad.setFechaVigencia(dto.getFechaVigencia());
        entidad.setPaciente(paciente);
        return entidad;
    }

    public static HistorialPacienteResponseDto toResponseDto(HistorialPaciente entidad) {
        HistorialPacienteResponseDto dto = new HistorialPacienteResponseDto();
        dto.setId(entidad.getId());
        dto.setPacienteId(entidad.getPaciente() != null ? entidad.getPaciente().getId() : null);
        dto.setEvento(entidad.getEvento());
        dto.setFecha(entidad.getFecha());
        dto.setObservacion(entidad.getObservacion());
        dto.setFechaVigencia(entidad.getFechaVigencia());
        dto.setVersion(entidad.getVersion());
        return dto;
    }

    public static List<HistorialPacienteResponseDto> toResponseDtoList(List<HistorialPaciente> entidades) {
        return entidades.stream()
                .map(HistorialPacienteMapper::toResponseDto)
                .collect(Collectors.toList());
    }
}
