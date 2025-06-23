package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.RecetaRequestDTO;
import com.imb2025.smedico.entity.Receta;

public interface IRecetaService {
	public List <Receta> findAll();
	public Receta findById(Long id);
	public Receta create(Receta receta);
    public Receta update(Long id,Receta receta) throws Exception;
	public void deleteById (Long id);
	public Receta fromDto(RecetaRequestDTO requestDto) throws Exception;
}
