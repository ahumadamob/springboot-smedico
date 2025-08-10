package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.entity.ObraSocial;

public interface IObraSocialService {

    List<ObraSocial> findAll();

    ObraSocial findById(Long id);

    ObraSocial create(ObraSocial obraSocial) throws Exception;

    ObraSocial update(Long id, ObraSocial obraSocial) throws Exception;

    void deleteById(Long id);

    ObraSocial fromDto(ObraSocialRequestDto dto) throws Exception;

    boolean existsById(Long id);

}
