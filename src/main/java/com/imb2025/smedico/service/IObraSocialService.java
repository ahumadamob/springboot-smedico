package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;

import java.util.List;

public interface IObraSocialService {

    List<ObraSocialResponseDto> findAll();

    ObraSocialResponseDto findById(Long id);

    ObraSocialResponseDto create(ObraSocialRequestDto obraSocial);

    ObraSocialResponseDto update(Long id, ObraSocialRequestDto obraSocial);

    void deleteById(Long id);

    boolean existsById(Long id);

    long countByCobertura(String cobertura);

    ObraSocialResponseDto findByNombre(String nombre);
}

