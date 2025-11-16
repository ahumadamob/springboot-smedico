package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.TurnoRequestDto;
import com.imb2025.smedico.dto.response.TurnoResponseDto;

import java.time.LocalDate;
import java.util.List;

public interface ITurnoService {
    List<TurnoResponseDto> findAll();
    TurnoResponseDto create(TurnoRequestDto dto);
    TurnoResponseDto update(Long id, TurnoRequestDto dto);
    TurnoResponseDto findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    List<TurnoResponseDto> findByFecha(LocalDate fecha);
    long countByFecha(LocalDate fecha);
}
