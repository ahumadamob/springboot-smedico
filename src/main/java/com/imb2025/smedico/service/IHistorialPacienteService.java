package com.imb2025.smedico.service;

import com.imb2025.smedico.entity.HistorialPaciente;

import java.time.LocalDate;
import java.util.List;

public interface IHistorialPacienteService {

    List<HistorialPaciente> findAll();
    
    HistorialPaciente create(HistorialPaciente historial);
    
    HistorialPaciente update(Long id, HistorialPaciente historialActualizado);

    HistorialPaciente findById(Long id);
    
    boolean existsById(Long id);
    
    void deleteById(Long id);

    List<HistorialPaciente> findByEvento(String evento);
    
    Long countByFecha(LocalDate fecha);
    
    List<HistorialPaciente> findVigentes(LocalDate fechaHoy);
    
    List<HistorialPaciente> findVencidos(LocalDate fechaHoy);
}
