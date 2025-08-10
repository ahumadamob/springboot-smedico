package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.entity.Afiliacion;

public interface IAfiliacionService {

    List<Afiliacion> findAll();

    Afiliacion findById(Long id);

    Afiliacion create(Afiliacion afiliacion) throws Exception;

    Afiliacion update(Long id, Afiliacion afiliacion) throws Exception;

    void deleteById(Long id);

    Afiliacion fromDto(AfiliacionRequestDto dto) throws Exception;

    boolean existsById(Long id);

}
