package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.HistorialPacienteRequestDto;
import com.imb2025.smedico.entity.HistorialPaciente;

import java.time.LocalDate;
import java.util.List;

public interface IHistorialPacienteService {

    List<HistorialPaciente> findAll();
    
    HistorialPaciente create(HistorialPacienteRequestDto dto);
    
    HistorialPaciente update(Long id, HistorialPacienteRequestDto dto);
    
    HistorialPaciente findById(Long id);
    
    boolean existsById(Long id);
    
    void deleteById(Long id);

    List<HistorialPaciente> findByEvento(String evento);
    
    Long countByFecha(LocalDate fecha);
}
