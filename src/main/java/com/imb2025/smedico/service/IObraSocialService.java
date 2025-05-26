package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.ObraSocial;

import java.util.List;
import java.util.Optional;

public interface IObraSocialService {
    List<ObraSocial> findAll();
    Optional<ObraSocial> findById(Long id);
    ObraSocial save(ObraSocial obraSocial);
    ObraSocial update(Long id, ObraSocial obraSocial);
    void deleteById(Long id);
}

