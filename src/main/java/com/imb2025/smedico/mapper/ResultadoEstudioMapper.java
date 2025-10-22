package com.imb2025.smedico.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.imb2025.smedico.dto.request.ResultadoEstudioRequestDto;
import com.imb2025.smedico.dto.response.ResultadoEstudioResponseDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.OrdenEstudioRepository;

public class ResultadoEstudioMapper {

	
	@Autowired
	private OrdenEstudioRepository ordenEstudioRepository;
	@Autowired
	private EstudioRepository estudioRepository;

	
    public ResultadoEstudio fromDto(ResultadoEstudioRequestDto requestDto) {
        OrdenEstudio ordenEstudio = ordenEstudioRepository.findById(requestDto.getOrdenEstudioId())
            .orElseThrow(() -> new ResourceNotFoundException("Orden de Estudio NO encontrado con ID " + requestDto.getOrdenEstudioId()));

        Estudio estudio = estudioRepository.findById(requestDto.getEstudioId())
            .orElseThrow(() -> new ResourceNotFoundException("Estudio NO encontrado con ID " + requestDto.getEstudioId()));

        ResultadoEstudio resultado = new ResultadoEstudio();
        resultado.setOrdenEstudio(ordenEstudio);
        resultado.setEstudio(estudio);
        resultado.setResultado(requestDto.getResultado());
        resultado.setFechaCarga(requestDto.getFechaCarga());
        resultado.setObservaciones(requestDto.getObservaciones());
        
        resultado.setAtributoBooleano(requestDto.getAtributoBooleano());
        return resultado;
    }
    
    public ResultadoEstudioResponseDto toDto(ResultadoEstudio resultadoEstudio) {
    	
    	ResultadoEstudioResponseDto dto = new ResultadoEstudioResponseDto();
    	
    	
    	dto.setEstudio(resultadoEstudio.getEstudio());
    	dto.setOrdenEstudio(resultadoEstudio.getOrdenEstudio());
    	dto.setFechaCarga(resultadoEstudio.getFechaCarga());
    	dto.setObservaciones(resultadoEstudio.getObservaciones());
    	dto.setResultado(resultadoEstudio.getResultado());
    	dto.setVersion(resultadoEstudio.getVersion());
    	
    	dto.setAtributoBooleano(resultadoEstudio.getAtributoBooleano());
    	return dto;
    }
	
}
