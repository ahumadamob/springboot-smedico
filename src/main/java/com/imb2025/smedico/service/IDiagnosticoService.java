package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.DiagnosticoRequestDto;
import com.imb2025.smedico.entity.Diagnostico;
import java.time.LocalDate;
import java.util.List;

public interface IDiagnosticoService {

    List<Diagnostico> findAll();
    Diagnostico findById(Long id);
    Diagnostico create(Diagnostico diagnostico);
    Diagnostico update(Long id, Diagnostico diagnostico);
    void deleteById(Long id);
    boolean existsById(Long id);

    // helper (si en algún lado lo usás)
    Diagnostico fromDto(DiagnosticoRequestDto dto);

    // TP7 que ya tenías
    List<Diagnostico> findByFechaDiagnostico(LocalDate fechaDiagnostico);
    long countByFechaDiagnostico(LocalDate fechaDiagnostico);

    // Ej.2
    boolean existsByIdentificadorLegibleIgnoreCase(String identificadorLegible);

    // Ej.3
    List<Diagnostico> findVigentes(LocalDate hoy);
    List<Diagnostico> findVencidos(LocalDate hoy);
}
