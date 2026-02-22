package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.SignosVitalesRequestDto;
import com.imb2025.smedico.dto.response.SignosVitalesResponseDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.SignosVitales;
import org.springframework.stereotype.Component;

@Component
public class SignosVitalesMapper {

	public SignosVitales fromDto(SignosVitalesRequestDto dto) {
		if (dto == null) {
			return null;
		}
		Consulta con = new Consulta ();
		con.setId(dto.getIdConsulta());
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
		s.setConsulta(con);

		return s;
	}


	public SignosVitalesResponseDto toDto(SignosVitales s) {

	    if (s == null) return null;

	    Long idConsulta = null;
	    if (s.getConsulta() != null) {
	        try {
	            idConsulta = s.getConsulta().getId();
	        } catch (Exception e) {
	            idConsulta = null;
	        }
	    }
	    
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
                idConsulta,
                s.getVersion()
            );
        }

}
