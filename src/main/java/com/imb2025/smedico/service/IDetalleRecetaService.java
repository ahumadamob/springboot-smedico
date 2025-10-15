package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.DetalleRecetaRequestDto;
import com.imb2025.smedico.dto.response.DetalleRecetaResponseDto;
import java.util.List;

public interface IDetalleRecetaService {
    List<DetalleRecetaResponseDto> findAll();
    DetalleRecetaResponseDto create(DetalleRecetaRequestDto detalleRecetaRequestDto);
    DetalleRecetaResponseDto update(Long id, DetalleRecetaRequestDto detalleRecetaRequestDto);
    DetalleRecetaResponseDto findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
}
