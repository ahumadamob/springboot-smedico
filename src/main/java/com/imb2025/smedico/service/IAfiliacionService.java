package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Afiliacion;
import java.util.List;

public interface IAfiliacionService {

    List<Afiliacion> findAll();

    Afiliacion findById(Long id);

    boolean existsById(Long id);

    Afiliacion create(Afiliacion afiliacion);

    Afiliacion update(Long id, Afiliacion afiliacion);

    void deleteById(Long id);

    List<Afiliacion> findByIdGreaterThan(Long idMin);

    long countByIdGreaterThan(Long idMin);
}