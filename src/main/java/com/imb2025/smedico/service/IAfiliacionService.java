package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.dto.AfiliacionRequestDTO;
import com.imb2025.smedico.entity.Afiliacion;

public interface  IAfiliacionService {
	
	public List<Afiliacion> findAll()throws Exception;
	public Afiliacion findById(Long id)throws Exception;
	public Afiliacion create(Afiliacion afiliacion) throws Exception ;
	public Afiliacion update(Long id, Afiliacion afiliacion) throws Exception ;
	public void deleteById(Long id) throws Exception;
	public Afiliacion fromDto(AfiliacionRequestDTO dto) ;

}
