package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.HorarioAtencionRequestDto;
import com.imb2025.smedico.entity.HorarioAtencion;
import java.util.List;

public interface IHorarioAtencionService {
    public List<HorarioAtencion> findAll();
    public HorarioAtencion create(HorarioAtencionRequestDto requestDto);
    public HorarioAtencion update(Long id, HorarioAtencionRequestDto requestDto);
    public HorarioAtencion findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public HorarioAtencion fromDto(HorarioAtencionRequestDto requestDto);
}