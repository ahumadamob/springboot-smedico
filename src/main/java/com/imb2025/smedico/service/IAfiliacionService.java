package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.DTO.AfiliacionRequestDTO;

import com.imb2025.smedico.entity.Afiliacion;

public interface  IAfiliacionService {
	
	public List<Afiliacion> findAll();
	public Afiliacion findById(Long id);
	public Afiliacion create(AfiliacionRequestDTO dto) throws Exception ;
	public Afiliacion update(Long id, AfiliacionRequestDTO dto) throws Exception ;
	public void deleteById(Long id);
	public Afiliacion dtoAfiliacion(AfiliacionRequestDTO dto) ;
	public Afiliacion save(Afiliacion afiliacion);
	public Afiliacion update(Long id, Afiliacion afiliacion);
	public void deleteById(Long id);

}
