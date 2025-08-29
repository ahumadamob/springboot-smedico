package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ConsultorioRequestDto;
import com.imb2025.smedico.entity.Consultorio;
import java.util.List;

import dto.ConsultorioRequestDTO;

public interface IConsultorioService {

    public List<Consultorio> findAll();
    public Consultorio create(Consultorio consultorio);
    public Consultorio update(Long id, Consultorio consultorio) throws Exception;
    public Consultorio findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Consultorio fromDto(ConsultorioRequestDto consultorioRequestDto) throws Exception;
}
