package com.imb2025.smedico.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.HistorialPaciente;

public interface HistorialPacienteRepository extends JpaRepository<HistorialPaciente, Long> {
	
    List<HistorialPaciente> findByEvento(String evento);

    Long countByFecha(LocalDate fecha);
    
    List<HistorialPaciente> findByFechaVigenciaGreaterThanEqual(LocalDate fecha);

    List<HistorialPaciente> findByFechaVigenciaLessThan(LocalDate fecha);
}
