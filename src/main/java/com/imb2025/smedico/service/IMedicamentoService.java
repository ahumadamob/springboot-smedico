package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.entity.Medicamento;
import java.util.List;

public interface IMedicamentoService {
    public List<Medicamento> findAll();
    public Medicamento create(MedicamentoRequestDto dto);
    public Medicamento update(Long id, MedicamentoRequestDto dto);
    public Medicamento findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public List<Medicamento> findActivos();
    public List<Medicamento> findInactivos();
    
}
