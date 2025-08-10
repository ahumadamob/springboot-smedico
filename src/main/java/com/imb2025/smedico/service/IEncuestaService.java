package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;
import java.util.List;

public interface IEncuestaService {
    public List<Encuesta> findAll();
    public Encuesta create(Encuesta encuesta);
    public Encuesta update(Long id, Encuesta encuesta) throws Exception;
    public Encuesta findById(Long id);
    public void deleteById(Long id);
    public Encuesta fromDto(EncuestaRequestDto encuestaRequestDto) throws Exception;
}
