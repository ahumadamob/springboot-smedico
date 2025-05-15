package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.ResultadoEstudio;

public interface IResultadoEstudioService {

	
	public List<ResultadoEstudio> findAll();
	public ResultadoEstudio findById(long id);
	public ResultadoEstudio save(ResultadoEstudio resultadoEstudio);
	public void deleteById(long id);
}
