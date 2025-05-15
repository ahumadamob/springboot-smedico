package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Receta;

public interface IRecetaService {
	public List <Receta> findAll();
	public Receta findById(Long id);
	public Receta save (Receta receta);
	public void deleteById (Long id);
	

}
