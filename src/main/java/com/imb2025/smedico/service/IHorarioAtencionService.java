package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.entity.HorarioAtencion;

public interface IHorarioAtencionService {
	    List<HorarioAtencion> getAllHorarioAtencion();
	    HorarioAtencion getHorarioAtencionById(Long id);
	    HorarioAtencion save(HorarioAtencion horarioAtencionEntity);
	    void deleteHorarioAtencion(Long id);
	    boolean existsById(Long id);
}
