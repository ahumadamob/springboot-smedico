package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.entity.Medicamento;
import java.util.List;

public interface IMedicamentoService {
    public List<Medicamento> findAll();
    public Medicamento create(Medicamento medicamento);
    public Medicamento update(Long id, Medicamento medicamento);
    public Medicamento findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public Medicamento fromDto(MedicamentoRequestDto medicamentoRequestDto);
    public List<Medicamento> findByNombre(String nombre);
    
    public Long countByPresentacion(String presentacion);	
}
