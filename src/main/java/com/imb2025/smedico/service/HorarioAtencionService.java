package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.HorarioAtencionEntity;

public interface HorarioAtencionService {

	    public List<HorarioAtencionEntity> getAllHorarioAtencion() ;
	    
	    public HorarioAtencionEntity getHorarioAtencionById(Long id);
	    
	    public HorarioAtencionEntity createHorarioAtencion( HorarioAtencionEntity horarioAtencionEntity);
	    
	    public HorarioAtencionEntity updateHorarioAtencion(Long id, HorarioAtencionEntity horarioAtencionEntity);
	    
	    public void deleteHorarioAtencion(Long id);	
}
