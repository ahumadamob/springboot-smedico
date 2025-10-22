package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.DireccionPacienteRequestDTO;
import com.imb2025.smedico.dto.response.DireccionPacienteResponseDTO;
import com.imb2025.smedico.entity.DireccionPaciente;

public class DireccionPacienteMapper {

    public static DireccionPaciente fromDto(DireccionPacienteRequestDTO dto) {
        DireccionPaciente entidad = new DireccionPaciente();
        entidad.setCalle(dto.getCalle());
        entidad.setNumero(dto.getNumero());
        entidad.setLocalidad(dto.getLocalidad());
        entidad.setProvincia(dto.getProvincia());
        entidad.setIdentificadorLegible(dto.getIdentificadorLegible());
        return entidad;
    }

    public static DireccionPacienteResponseDTO toResponseDto(DireccionPaciente entidad) {
        DireccionPacienteResponseDTO dto = new DireccionPacienteResponseDTO();
        dto.setId(entidad.getId());
        dto.setCalle(entidad.getCalle());
        dto.setNumero(entidad.getNumero());
        dto.setLocalidad(entidad.getLocalidad());
        dto.setProvincia(entidad.getProvincia());
        dto.setIdentificadorLegible(entidad.getIdentificadorLegible());
        dto.setVersion(entidad.getVersion());
        return dto;
    }
    
 // Actualiza una entidad existente con los datos de un DTO
    public static void updateEntityFromDto(DireccionPaciente entidad, DireccionPacienteRequestDTO dto) {
        entidad.setCalle(dto.getCalle());
        entidad.setNumero(dto.getNumero());
        entidad.setLocalidad(dto.getLocalidad());
        entidad.setProvincia(dto.getProvincia());
        entidad.setIdentificadorLegible(dto.getIdentificadorLegible());
    }
}
