package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.MotivoCancelacionRequestDto;
import com.imb2025.smedico.dto.response.MotivoCancelacionResponseDto;
import com.imb2025.smedico.entity.MotivoCancelacion;

public class MotivoCancelacionMapper {

    public MotivoCancelacion fromDto(MotivoCancelacionRequestDto dto) {
        MotivoCancelacion motivoCancelacion = new MotivoCancelacion();
        motivoCancelacion.setNombre(dto.getNombre());
        motivoCancelacion.setDescripcion(dto.getDescripcion());
        return motivoCancelacion;
    }
    
    public MotivoCancelacionResponseDto toDto(MotivoCancelacion motivoCancelacion) {
    	
    	MotivoCancelacionResponseDto dto = new MotivoCancelacionResponseDto();
    	dto.setDescripcion(motivoCancelacion.getDescripcion());
    	dto.setNombre(motivoCancelacion.getNombre());
    	dto.setVersion(motivoCancelacion.getVersion());
    	
    	return dto;
    }
}
