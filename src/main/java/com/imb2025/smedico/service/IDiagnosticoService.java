package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.DiagnosticoRequestDTO;
import com.imb2025.smedico.entity.Diagnostico;

import java.util.List;

public interface IDiagnosticoService {
    List<Diagnostico> findAll();
    Diagnostico findById(Long id);
    Diagnostico save(Diagnostico diagnostico);
    void deleteById(Long id);
    boolean existsById(Long id);
    Diagnostico actualizar(Long id, Diagnostico diagnostico);
    Diagnostico fromDto(DiagnosticoRequestDTO dto); // <-- ESTA ES LA NUEVA LÍNEA
}


