package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EstudioRequestDto;
import com.imb2025.smedico.entity.Estudio;

public interface IEstudioService {

    List<Estudio> findAll();

    Estudio findById(Long id);

    Estudio create(Estudio estudio) throws Exception;

    Estudio update(Long id, Estudio estudio) throws Exception;

    void deleteById(Long id);

    Estudio fromDto(EstudioRequestDto dto) throws Exception;

    boolean existsById(Long id);

}
