package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.HorarioAtencion;

public interface IHorarioAtencionService {

	    public List<HorarioAtencion> getAllHorarioAtencion() ;
	    
	    public HorarioAtencion getHorarioAtencionById(Long id);
	    
	    public HorarioAtencion save( HorarioAtencion horarioAtencionEntity);
	    
	    public void deleteHorarioAtencion(Long id);	
}
