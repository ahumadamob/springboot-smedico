package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DiagnosticoRequestDto;
import com.imb2025.smedico.entity.Diagnostico;
import java.util.List;

public interface IDiagnosticoService {
    public List<Diagnostico> findAll();
    public Diagnostico create(Diagnostico diagnostico);
    public Diagnostico update(Long id, Diagnostico diagnostico);
    public Diagnostico findById(Long id);
    public void deleteById(Long id);
    public Diagnostico fromDto(DiagnosticoRequestDto diagnosticoRequestDto);
}
