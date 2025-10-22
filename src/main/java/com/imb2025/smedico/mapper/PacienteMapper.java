package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.PacienteRequestDto;
import com.imb2025.smedico.dto.response.PacienteResponseDto;
import com.imb2025.smedico.entity.Paciente;

public class PacienteMapper {
	

    public static Paciente fromDto(PacienteRequestDto dto) {
        Paciente paciente = new Paciente();
        paciente.setNombre(dto.getNombre());
        paciente.setApellido(dto.getApellido());
        paciente.setDni(dto.getDni());
        paciente.setEmail(dto.getEmail());
        paciente.setFechaNacimiento(dto.getFechaNacimiento());
        paciente.setTelefono(dto.getTelefono());
        paciente.setActivo(dto.isActivo());
        return paciente;
    }

    public static PacienteResponseDto toResponseDto(Paciente paciente) {
        PacienteResponseDto dto = new PacienteResponseDto();
        dto.setId(paciente.getId());
        dto.setNombre(paciente.getNombre());
        dto.setApellido(paciente.getApellido());
        dto.setDni(paciente.getDni());
        dto.setEmail(paciente.getEmail());
        dto.setVersion(paciente.getVersion()); 
        dto.setActivo(paciente.isActivo());
        return dto;
    }
}	
	
