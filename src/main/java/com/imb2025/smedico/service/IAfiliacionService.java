package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.entity.Afiliacion;
import java.util.List;

public interface IAfiliacionService {

    List<Afiliacion> findAll();

    Afiliacion create(Afiliacion afiliacion);

    Afiliacion update(Long id, Afiliacion afiliacion);

    Afiliacion findById(Long id);

    boolean existsById(Long id);

    void deleteById(Long id);

    Afiliacion fromDto(AfiliacionRequestDto afiliacionRequestDto);
}
