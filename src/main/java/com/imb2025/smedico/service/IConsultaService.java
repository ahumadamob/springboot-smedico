package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import java.util.List;

public interface IConsultaService {
    List<Consulta> findAll();
    Consulta findById(Long id);

    Consulta createFromDto(ConsultaRequestDto dto);
    Consulta updateFromDto(Long id, ConsultaRequestDto dto);
    void deleteById(Long id);

    boolean existsById(Long id);
}
