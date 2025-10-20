package com.imb2025.smedico.service;

import java.time.LocalDate;
import java.util.List;

import com.imb2025.smedico.dto.request.SignosVitalesRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.SignosVitales;

public interface ISignosVitalesService {
    List<SignosVitales> findAll();
    SignosVitales create(SignosVitalesRequestDto dto);
    SignosVitales update(Long id, SignosVitalesRequestDto dto);
    SignosVitales findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    List<SignosVitales> findByFechas(LocalDate inicio, LocalDate fin);
    Long countByConsulta(Long idConsulta);
}
