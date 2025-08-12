package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import java.util.List;

public interface IConsultaService {
    public List<Consulta> findAll();
    public Consulta create(Consulta consulta);
    public Consulta update(Long id, Consulta consulta);
    public Consulta findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Consulta fromDto(ConsultaRequestDto consultaRequestDto);
}
