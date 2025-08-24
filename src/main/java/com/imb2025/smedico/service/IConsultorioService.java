package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ConsultorioRequestDto;
import com.imb2025.smedico.entity.Consultorio;
import java.util.List;

import dto.ConsultorioRequestDTO;

public interface IConsultorioService {
	
	//Crear
	public Consultorio create(Consultorio consultorio);
	//Listar
	public List<Consultorio> findAll();
	//Eliminar por id
	public void deleteById(Long id);
	//Buscar por id
	public Consultorio findById(Long id);
	//Actualizar
	public Consultorio update(Long id, Consultorio fromDto) throws Exception;
	public Consultorio fromDto(ConsultorioRequestDTO consultorioRequestDto) throws Exception;
	boolean existsById(Long id);
	
}
