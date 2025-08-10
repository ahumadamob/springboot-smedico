package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.entity.Medicamento;
import java.util.List;

public interface IMedicamentoService {
    public List<Medicamento> findAll();
    public Medicamento create(Medicamento medicamento) throws Exception;
    public Medicamento update(Long id, Medicamento medicamento) throws Exception;
    public Medicamento findById(Long id);
    public void deleteById(Long id);
    public Medicamento fromDto(MedicamentoRequestDto medicamentoRequestDto) throws Exception;
}
