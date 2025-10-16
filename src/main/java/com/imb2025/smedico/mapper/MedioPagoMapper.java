package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.dto.response.MedioPagoResponseDto;
import com.imb2025.smedico.entity.MedioPago;

public class MedioPagoMapper {
	
	public static MedioPago fromDto(MedioPagoRequestDto dto) {
        MedioPago medioPago = new MedioPago();
        medioPago.setNombre(dto.getNombre());
        medioPago.setTipo(dto.getTipo());
        return medioPago;
    }

	public static MedioPagoResponseDto toResponseDto(MedioPago entity) {
	    MedioPagoResponseDto dto = new MedioPagoResponseDto();
	    //dto.setId(entity.getId());
	    dto.setNombre(entity.getNombre());
	    dto.setTipo(entity.getTipo()); // ← como enum
	    dto.setVersion(entity.getVersion());
	    return dto;
	}

	

}
