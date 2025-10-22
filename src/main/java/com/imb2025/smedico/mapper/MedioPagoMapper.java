package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.dto.response.MedioPagoResponseDto;
import com.imb2025.smedico.entity.MedioPago;

public class MedioPagoMapper {
	
	public MedioPago fromDto(MedioPagoRequestDto dto) {
        MedioPago medioPago = new MedioPago();
        medioPago.setNombre(dto.getNombre());
        medioPago.setTipo(dto.getTipo());
        medioPago.setEstado(dto.getEstado()); // a

        return medioPago;
    }

	public MedioPagoResponseDto toResponseDto(MedioPago mediopago) {
	    MedioPagoResponseDto dtoResponse = new MedioPagoResponseDto();
	    dtoResponse.setId(mediopago.getId());
	    dtoResponse.setNombre(mediopago.getNombre());
	    dtoResponse.setTipo(mediopago.getTipo()); 
	    dtoResponse.setVersion(mediopago.getVersion());
	    dtoResponse.setEstado(mediopago.getEstado()); // a
	    return dtoResponse;
	}

	

}
