package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;

public interface IEncuestaService {

    // Búsquedas básicas
    List<Encuesta> findAll();
    Encuesta findById(Long id);

    // CRUD (reciben/retornan entidad)
    Encuesta create(Encuesta encuesta);
    Encuesta update(Long id, Encuesta encuesta);
    void deleteById(Long id);
    boolean existsById(Long id);

    // Mapper interno (desde DTO a entidad) — lo usa el Controller
    Encuesta fromDto(EncuestaRequestDto dto);

    // TP07: métodos “mágicos” expuestos por Service (delegan al repo)
    List<Encuesta> findByPuntajeGreaterThanEqual(int puntajeMin);
    long countByConsulta(Long consultaId);
}
