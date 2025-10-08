package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.SignosVitales;
import com.imb2025.smedico.dto.SignosVitalesRequestDto;

public interface ISignosVitalesService {
    List<SignosVitales> findAll();
    SignosVitales create(SignosVitales signosVitales);
    SignosVitales update(Long id, SignosVitales signosVitales);
    SignosVitales findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    SignosVitales fromDto(SignosVitalesRequestDto signosVitalesRequestDto);
    SignosVitalesRequestDto toDto(SignosVitales signosVitales);
}
