package com.imb2025.smedico.mapper;

import java.util.List;

import com.imb2025.smedico.dto.request.ConsultorioRequestDto;
import com.imb2025.smedico.dto.response.ConsultorioResponseDto;
import com.imb2025.smedico.entity.Consultorio;

public class ConsultorioMapper {
	
	public Consultorio fromDto(ConsultorioRequestDto dto) {
		Consultorio consultorio = new Consultorio();
		consultorio.setNombre(dto.getNombre());
		consultorio.setPiso(dto.getPiso());
		consultorio.setUbicacion(dto.getUbicacion());
		return consultorio;
	}
	
	public ConsultorioResponseDto toDto(Consultorio consultorio) {
		ConsultorioResponseDto dto = new ConsultorioResponseDto();
		dto.setNombre(consultorio.getNombre());
		dto.setPiso(consultorio.getPiso());
		dto.setUbicacion(consultorio.getUbicacion());
		dto.setVersion(consultorio.getVersion());
		return dto;
	}

}
