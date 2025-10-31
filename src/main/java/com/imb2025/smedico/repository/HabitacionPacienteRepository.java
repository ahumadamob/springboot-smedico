package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.HabitacionPaciente;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionPacienteRepository extends JpaRepository<HabitacionPaciente, Long> {

    // Buscar habitaciones por sector
    List<HabitacionPaciente> findBySectorIgnoreCase(String sector);

    // Contar habitaciones por piso
    Long countBySectorIgnoreCase(String piso);
}
