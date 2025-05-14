package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.HorarioAtencion;

public interface HorarioAtencionService {

	    public List<HorarioAtencion> getAllHorarioAtencion() ;
	    
	    public HorarioAtencion getHorarioAtencionById(Long id);
	    
	    public HorarioAtencion createHorarioAtencion( HorarioAtencion horarioAtencionEntity);
	    
	    public HorarioAtencion updateHorarioAtencion(Long id, HorarioAtencion horarioAtencionEntity);
	    
	    public void deleteHorarioAtencion(Long id);	
}
