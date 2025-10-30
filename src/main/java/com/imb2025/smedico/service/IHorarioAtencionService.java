package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.HorarioAtencion;
import java.util.List;

public interface IHorarioAtencionService {
    public List<HorarioAtencion> findAll();
    public HorarioAtencion create(HorarioAtencion horarioAtencion) throws Exception;
    public HorarioAtencion update(Long id, HorarioAtencion horarioAtencion) throws Exception;
    public HorarioAtencion findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public long countHorariosByMedico(Long medicoId);
    public List<HorarioAtencion> findHorariosByDia(String diaSemana);
}
