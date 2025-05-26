package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedicoDtoRequest.HorarioAtencionRequestDTO;

public interface IHorarioAtencionService {

	    public List<HorarioAtencion> getAllHorarioAtencion() ;
	    
	    public HorarioAtencion getHorarioAtencionById(Long id);
	    
	    public HorarioAtencion create ( HorarioAtencionRequestDTO dto) throws Exception;
	    
	    public HorarioAtencion update ( HorarioAtencionRequestDTO dto,  Long id) throws Exception;
	    
	    public void deleteHorarioAtencion(Long id);	
}
