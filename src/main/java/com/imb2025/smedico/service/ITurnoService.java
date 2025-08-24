package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.TurnoRequestDto;
import com.imb2025.smedico.entity.Turno;
import java.util.List;

public interface ITurnoService {
    public List<Turno> findAll();
    public Turno create(Turno turno) throws Exception;
    public Turno update(Long id, Turno turno) throws Exception;
    public Turno findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Turno fromDto(TurnoRequestDto turnoRequestDto);
}
