package com.imb2025.smedico.service;

import java.time.LocalDate;
import java.util.List;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;

public interface IObraSocialService {

    List<ObraSocialResponseDto> findAll();

    ObraSocialResponseDto findById(Long id);

    ObraSocialResponseDto findByNombre(String nombre);

    ObraSocialResponseDto create(ObraSocialRequestDto dto);

    ObraSocialResponseDto update(Long id, ObraSocialRequestDto dto);

    void deleteById(Long id);

    boolean existsById(Long id);

    long countByCobertura(String cobertura);

	void delete(Long id);
	
	List<ObraSocialResponseDto> findVigentes(LocalDate fecha);
	List<ObraSocialResponseDto> findVencidos(LocalDate fecha);

	List<ObraSocialResponseDto> findVencidos();

	List<ObraSocialResponseDto> findVigentes();

}
