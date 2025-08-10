package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.entity.ObraSocial;
import java.util.List;

public interface IObraSocialService {
    public List<ObraSocial> findAll();
    public ObraSocial create(ObraSocial obraSocial) throws Exception;
    public ObraSocial update(Long id, ObraSocial obraSocial) throws Exception;
    public ObraSocial findById(Long id);
    public void deleteById(Long id);
    public ObraSocial fromDto(ObraSocialRequestDto obraSocialRequestDto) throws Exception;
}
