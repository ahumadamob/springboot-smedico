package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.dto.response.MedioPagoResponseDto;
import com.imb2025.smedico.entity.MedioPago;

public class MedioPagoMapper {
	
	public MedioPago fromDto(MedioPagoRequestDto dto) {
        MedioPago medioPago = new MedioPago();
        medioPago.setNombre(dto.getNombre());
        medioPago.setTipo(dto.getTipo());
        return medioPago;
    }

	public MedioPagoResponseDto toResponseDto(MedioPago mediopago) {
	    MedioPagoResponseDto dto = new MedioPagoResponseDto();
	    dto.setNombre(mediopago.getNombre());
	    dto.setTipo(mediopago.getTipo()); // ← como enum
	    dto.setVersion(mediopago.getVersion());
	    return dto;
	}

	

}
