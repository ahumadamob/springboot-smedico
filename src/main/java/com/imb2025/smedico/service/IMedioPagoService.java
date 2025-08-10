package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import java.util.List;

public interface IMedioPagoService {
    public List<MedioPago> findAll();
    public MedioPago create(MedioPago medioPago);
    public MedioPago update(Long id, MedioPago medioPago);
    public MedioPago findById(Long id);
    public void deleteById(Long id);
    public MedioPago fromDto(MedioPagoRequestDto medioPagoRequestDto);
}
