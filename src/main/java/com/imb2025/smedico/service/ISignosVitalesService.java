package com.imb2025.smedico.service;

import java.time.LocalDate;
import java.util.List;

import com.imb2025.smedico.entity.SignosVitales;

public interface ISignosVitalesService {
    List<SignosVitales> findAll();
    SignosVitales create(SignosVitales sig);
    SignosVitales update(Long id, SignosVitales sig);
    SignosVitales findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    List<SignosVitales> findByFechas(LocalDate inicio, LocalDate fin);
    Long countByConsulta(Long idConsulta);
    List<SignosVitales> findVigentes(LocalDate fecha);
    List<SignosVitales> findVencidos(LocalDate fecha);

}
