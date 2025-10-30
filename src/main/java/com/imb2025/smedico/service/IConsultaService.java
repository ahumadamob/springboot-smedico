package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import java.time.LocalDate;                 // 👈 usa LocalDate
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IConsultaService {

	
    List<Consulta> findAll();
    Consulta findById(Long id);
    Consulta createFromDto(ConsultaRequestDto dto);
    Consulta updateFromDto(Long id, ConsultaRequestDto dto);
    void deleteById(Long id);
    boolean existsById(Long id);

    // 1) Filtro (findBy...)  👇 con LocalDate
    Page<Consulta> findByFechaBetween(LocalDate desde, LocalDate hasta, Pageable pageable);

    // 2) Conteo (countBy...)
    long countByPacienteId(Long pacienteId);
}
