package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.ObraSocial;

import obrasocialrequestdto.ObraSocialRequestDTO;

import java.util.List;
import java.util.Optional;

public interface IObraSocialService {
    List<ObraSocial> findAll();
    Optional<ObraSocial> findById(Long id);
    ObraSocial create(ObraSocial obraSocial);
    ObraSocial update(Long id, ObraSocial obraSocial) throws Exception;
    void deleteById(Long id);
    
    ObraSocial fromDto(ObraSocialRequestDTO dto);

    
	static Optional<ObraSocial> buscarPorId(Long id) {
		return null;
	}
	static void guardar(ObraSocial obraSocial) {
		
		
	}
	ObraSocial guardar(ObraSocialRequestDTO dto);
	ObraSocial save(ObraSocial obraSocial);

}

