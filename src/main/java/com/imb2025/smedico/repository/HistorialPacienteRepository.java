package com.imb2025.smedico.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.HistorialPaciente;

public interface HistorialPacienteRepository extends JpaRepository<HistorialPaciente, Long> {
	
    // Encuentra todos los historiales por evento
    List<HistorialPaciente> findByEvento(String evento);

    // Cuenta cuántos historiales existen en una fecha específica
    Long countByFecha(LocalDate fecha);
}
