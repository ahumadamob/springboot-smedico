package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.AsistenteRequestDto;
import com.imb2025.smedico.dto.response.AsistenteResponseDto;
import com.imb2025.smedico.entity.Asistente;

public class AsistenteMapper {

    public Asistente fromDto(AsistenteRequestDto dto) {
        Asistente asistente = new Asistente();
        asistente.setApellido(dto.getApellido());
        asistente.setNombre(dto.getNombre());
        asistente.setEmail(dto.getEmail());
        asistente.setTelefono(dto.getTelefono());
        asistente.setDni(dto.getDni());
        asistente.setEsSupervisor(dto.getEsSupervisor());
        return asistente;
    }
    
    public AsistenteResponseDto toDto(Asistente asistente) {
    	
    	AsistenteResponseDto dto = new AsistenteResponseDto();
    	dto.setApellido(asistente.getApellido());
    	dto.setDni(asistente.getDni());
    	dto.setEmail(asistente.getEmail());
    	dto.setId(asistente.getId());
    	dto.setNombre(asistente.getNombre());
    	dto.setVersion(asistente.getVersion());
        dto.setEsSupervisor(asistente.getEsSupervisor());
   	
    	return dto;
    }
}