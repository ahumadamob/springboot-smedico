package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Asistente;

public interface IAsistenteService {

	public List<Asistente> findAll();
	public Asistente findById(Long id);
	public Asistente save(Asistente asistenteentity);
	public void deleteById(Long id);
	public Asistente update(Long id, Asistente asistenteActualizado);
}
