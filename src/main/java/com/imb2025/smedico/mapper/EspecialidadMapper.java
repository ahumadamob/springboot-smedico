package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.EspecialidadRequestDto;
import com.imb2025.smedico.dto.response.EspecialidadResponseDto;
import com.imb2025.smedico.entity.Especialidad;

public class EspecialidadMapper {
	
	public Especialidad fromDto(EspecialidadRequestDto dto) {
		
	    Especialidad especialidad = new Especialidad();
	    especialidad.setNombre(dto.getNombre());
	    especialidad.setDescripcion(dto.getDescripcion());
	    especialidad.setAtributoBooleano(dto.getAtributoBooleano());
	    especialidad.setAlias(dto.getAlias());
	  
	    return especialidad;
}
	public EspecialidadResponseDto toDto(Especialidad especialidad) {
		EspecialidadResponseDto dto = new EspecialidadResponseDto();
		dto.setId(especialidad.getId());
		dto.setNombre(especialidad.getNombre());
		dto.setDescripcion(especialidad.getDescripcion());
		dto.setVersion(especialidad.getVersion());
		dto.setAtributoBuleano(especialidad.getAtributoBooleano());
		dto.setAlias(especialidad.getAlias());
		
		return dto;
	}

}
