package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.TurnoRequestDto;
import com.imb2025.smedico.entity.Turno;
import java.util.List;

public interface ITurnoService {
    List<Turno> findAll();
    Turno create(Turno turno);
    Turno update(Long id, Turno turno);
    Turno findById(Long id);
    boolean existsById(Long id);
    void deleteById(Long id);
    Turno fromDto(TurnoRequestDto turnoRequestDto);
}
