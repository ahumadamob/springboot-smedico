package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Consulta;
import java.util.List;

public interface IConsultaService {
    List<Consulta> findAll();
    Consulta findById(Long id);
    Consulta save(ConsultaRequestDTO dto);
    Consulta update(Long id, ConsultaRequestDTO dto);
    void deleteById(Long id);
}

