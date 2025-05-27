package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.ResultadoEstudioRequestDTO;
import com.imb2025.smedico.entity.ResultadoEstudio;

public interface IResultadoEstudioService {

	
	public List<ResultadoEstudio> findAll();
	public ResultadoEstudio findById(long id);
	public ResultadoEstudio create(ResultadoEstudio resultadoEstudio);
	public ResultadoEstudio update(long id, ResultadoEstudio resultadoEstudio) throws Exception;
	public void deleteById(long id);
	public ResultadoEstudio fromDto(ResultadoEstudioRequestDTO requestDto) throws Exception;
}
