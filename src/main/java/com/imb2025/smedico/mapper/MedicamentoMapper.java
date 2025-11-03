package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.MedicamentoRequestDto;
import com.imb2025.smedico.dto.response.MedicamentoResponseDto;
import com.imb2025.smedico.entity.Medicamento;

public class MedicamentoMapper {
	
	public Medicamento fromDto(MedicamentoRequestDto dto) {
		Medicamento medicamento = new Medicamento();
	    medicamento.setNombre(dto.getNombre());
	    medicamento.setDosisSugerida(dto.getDosisSugerida());
	    medicamento.setPresentacion(dto.getPresentacion());
	    return medicamento;
	}
	
    public MedicamentoResponseDto toDto(Medicamento medicamento) {

    	MedicamentoResponseDto dto = new MedicamentoResponseDto();
    	dto.setNombre(medicamento.getNombre());
    	dto.setDosisSugerida(medicamento.getDosisSugerida());
    	dto.setPresentacion(medicamento.getPresentacion());
    	dto.setVersion(medicamento.getVersion());
    	
    	return dto;
    }
		

}
