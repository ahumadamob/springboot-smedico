package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;

public interface IEncuestaService {

    List<Encuesta> findAll();

    Encuesta findById(Long id);

    Encuesta create(Encuesta encuesta);

    Encuesta update(Long id, Encuesta encuesta);

    Encuesta fromDto(EncuestaRequestDto dto);

    void deleteById(Long id);

    boolean existsById(Long id);
}
