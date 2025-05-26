package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.Encuesta;


public interface IEncuestaService {   
	public List<Encuesta> findAll();
	public Encuesta findById(Long id);
	public Encuesta save(Encuesta encuesta);
	public void deleteById(Long id);	
}
