package com.imb2025.smedico.dto.mapper;

import com.imb2025.smedico.dto.request.SignosVitalesRequestDto;
import com.imb2025.smedico.dto.response.SignosVitalesResponseDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.SignosVitales;

public class SignosVitalesMapper {

	public SignosVitales fromDto(SignosVitalesRequestDto dto, Consulta consulta) {
		if (dto == null) {
			return null;
		}
		
		SignosVitales s = new SignosVitales();
		s.setFechaHora(dto.getFechaHora());
		s.setPeso(dto.getPeso());
		s.setAltura(dto.getAltura());
		s.setImc(dto.getImc());
		s.setTemperatura(dto.getTemperatura());
		s.setFrecuenciaCardiaca(dto.getFrecuenciaCardiaca());
		s.setPresionSistolica(dto.getPresionSistolica());
		s.setPresionDiastolica(dto.getPresionDiastolica());
		s.setSaturacionO2(dto.getSaturacionO2());
		s.setObservaciones(dto.getObservaciones());
		s.setConsulta(consulta);

		return s;
	}

	public SignosVitalesResponseDto toDto(SignosVitales s) {
		if (s == null) {
			return null;
		} else {
			return new SignosVitalesResponseDto(
	                s.getId(),
	                s.getFechaHora(),
	                s.getPeso(),
	                s.getAltura(),
	                s.getImc(), 
	                s.getTemperatura(),
	                s.getFrecuenciaCardiaca(),
	                s.getPresionSistolica(),
	                s.getPresionDiastolica(),
	                s.getSaturacionO2(),
	                s.getObservaciones(),
	                s.getConsulta().getId(),
	                s.getVersion());
		}

	}
}
