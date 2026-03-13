package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.entity.Afiliacion;

import java.util.List;

public interface IAfiliacionService {

    List<Afiliacion> findAll();

    Afiliacion create(Afiliacion afiliacion);

    Afiliacion update(Long id, Afiliacion afiliacion);

    Afiliacion findById(Long id);
    
    Afiliacion findByDescripcionCorta(String DescripcionCorta);
    
    boolean existsByDescripcionCorta(String DescripcionCorta);

    boolean existsById(Long id);

    void deleteById(Long id);

    Afiliacion fromDto(AfiliacionRequestDto afiliacionRequestDto);

    // métodos "mágicos" del repository
    List<Afiliacion> findByIdGreaterThan(Long idMin);

    long countByIdGreaterThan(Long idMin);
}
