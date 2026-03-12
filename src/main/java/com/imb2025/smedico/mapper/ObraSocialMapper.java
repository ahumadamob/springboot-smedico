package com.imb2025.smedico.mapper;

import org.springframework.stereotype.Component;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;
import com.imb2025.smedico.entity.ObraSocial;

@Component
public class ObraSocialMapper {

public ObraSocial fromDto(ObraSocialRequestDto dto) {
	
	if (dto == null) return null; 
	
	ObraSocial obs = new ObraSocial();
	
	obs.setCobertura(dto.getCobertura());
	obs.setDireccion(dto.getDireccion());
	obs.setNombre(dto.getNombre());
	obs.setTelefono(dto.getTelefono());
	obs.setCodigoReferencia(dto.getCodigoReferencia());
	
	return obs;
}
public ObraSocialResponseDto toResponseDto(ObraSocial obs) {

    if (obs == null) return null;

    ObraSocialResponseDto response = new ObraSocialResponseDto();

    response.setId(obs.getId());
    response.setNombre(obs.getNombre());
    response.setTelefono(obs.getTelefono());
    response.setDireccion(obs.getDireccion());
    response.setCobertura(obs.getCobertura());
    response.setCodigoReferencia(obs.getCodigoReferencia());

    return response;
}

	
}
