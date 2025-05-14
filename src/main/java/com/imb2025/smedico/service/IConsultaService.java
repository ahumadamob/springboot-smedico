package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.Consulta;
import java.util.List;

public interface IConsultaService {
    List<Consulta> findAll();
    Consulta findById(Long id);
    Consulta save(Consulta consulta);
    Consulta update(Consulta consulta);
    void deleteById(Long id);
}

