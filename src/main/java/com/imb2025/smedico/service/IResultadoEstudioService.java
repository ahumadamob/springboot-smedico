package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.ResultadoEstudioRequestDto;
import com.imb2025.smedico.entity.ResultadoEstudio;

import java.time.LocalDate;
import java.util.List;

public interface IResultadoEstudioService {
    public List<ResultadoEstudio> findAll();
    public ResultadoEstudio create(ResultadoEstudio resultadoEstudio);
    public ResultadoEstudio update(Long id, ResultadoEstudio resultadoEstudio);
    public ResultadoEstudio findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
 
    
    public List<ResultadoEstudio> findByFechaCarga(LocalDate fechaCarga);
	public long countByFechaCarga(LocalDate fechaCarga);
	public List<ResultadoEstudio> listarTrue();
	public List<ResultadoEstudio> listarFalse();
	
	
}
