package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.HorarioAtencion;
import java.util.List;

public interface IHorarioAtencionService {
    public List<HorarioAtencion> findAll();
    public HorarioAtencion create(HorarioAtencion horarioAtencion);
    public HorarioAtencion update(Long id, HorarioAtencion horarioAtencion);
    public HorarioAtencion findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
}