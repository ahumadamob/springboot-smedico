package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.HorarioAtencionRequestDto;
import com.imb2025.smedico.entity.HorarioAtencion;
import java.util.List;

public interface IHorarioAtencionService {

    List<HorarioAtencion> findAll();
    HorarioAtencion findById(Long id);
    HorarioAtencion create(HorarioAtencion horarioAtencion) throws Exception;
    HorarioAtencion update(Long id, HorarioAtencion horarioAtencion) throws Exception;
    void deleteById(Long id);
    HorarioAtencion fromDto(HorarioAtencionRequestDto dto) throws Exception;
    boolean existsById(Long id);
}
