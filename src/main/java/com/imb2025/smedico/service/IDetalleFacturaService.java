package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.DetalleFacturaRequestDto;
import com.imb2025.smedico.dto.response.DetalleFacturaResponseDto;
import java.util.List;

public interface IDetalleFacturaService {
    List<DetalleFacturaResponseDto> findAll();
    DetalleFacturaResponseDto create(DetalleFacturaRequestDto dto);
    DetalleFacturaResponseDto update(Long id, DetalleFacturaRequestDto dto);
    DetalleFacturaResponseDto findById(Long id);
    List<DetalleFacturaResponseDto> findVigentes();
List<DetalleFacturaResponseDto> findVencidos();

}
