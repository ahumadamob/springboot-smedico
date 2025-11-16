package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Receta;

import java.time.LocalDate;
import java.util.List;

public interface IRecetaService {
    public List<Receta> findAll();
    public Receta create(Receta receta);
    public Receta update(Long id, Receta receta);
    public Receta findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
	public List<Receta> findByFecha(LocalDate fecha);
	long countByFecha(LocalDate fecha);
}	
