package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;

public interface IEncuestaService {

    List<Encuesta> findAll();
    Encuesta findById(Long id);

    Encuesta create(Encuesta encuesta);
    Encuesta update(Long id, Encuesta encuesta);
    void deleteById(Long id);
    boolean existsById(Long id);

    Encuesta fromDto(EncuestaRequestDto dto);

    // TP07
    List<Encuesta> findByPuntajeGreaterThanEqual(int puntajeMin);
    long countByConsulta(Long consultaId);
}
