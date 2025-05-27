package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.MedicoRequestDTO;
import com.imb2025.smedico.entity.Medico;

public interface IMedicoService {
	
	public List <Medico> findAll();
	public Medico findById(Long id);
	public void deleteById(Long id); 
	public Medico update(Long id, MedicoRequestDTO dto) throws Exception;
	public Medico create(MedicoRequestDTO dto);
	

}
