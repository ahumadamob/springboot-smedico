package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.Medico;

public interface IMedicoService {
	
	public List <Medico> findAll();
	public Medico findById(Long id);
	public Medico save(Medico medico);
	public void deleteById(Long id); 

}
