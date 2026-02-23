package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.ConsultorioRequestDto;
import com.imb2025.smedico.dto.response.ConsultorioResponseDto;
import com.imb2025.smedico.entity.Consultorio;

public class ConsultorioMapper {
	
	public Consultorio fromDto(ConsultorioRequestDto dto) {
		Consultorio consultorio = new Consultorio();
		consultorio.setNombre(dto.getNombre());
		consultorio.setUbicacion(dto.getUbicacion());
		consultorio.setPiso(dto.getPiso());
		consultorio.setIdentificadorLegible(dto.getIdentificadorLegible());
		return consultorio;
	}
	
	public ConsultorioResponseDto toDto(Consultorio consultorio) {
		ConsultorioResponseDto dto = new ConsultorioResponseDto();
		dto.setNombre(consultorio.getNombre());
		dto.setUbicacion(consultorio.getUbicacion());
		dto.setPiso(consultorio.getPiso());
		dto.setIdentificador_legible(consultorio.getIdentificadorLegible());
		dto.setVersion(consultorio.getVersion());
		return dto;
	}

}
