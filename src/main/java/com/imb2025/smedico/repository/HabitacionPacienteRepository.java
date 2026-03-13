package com.imb2025.smedico.repository;


import com.imb2025.smedico.entity.HabitacionPaciente;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionPacienteRepository extends JpaRepository<HabitacionPaciente, Long> {

	Optional<HabitacionPaciente> findByIdentificadorLegibleIgnoreCase(String identificadorLegible);

    List<HabitacionPaciente> findByFechaVigenciaGreaterThanEqual(LocalDate fecha);

    List<HabitacionPaciente> findByFechaVigenciaLessThan(LocalDate fecha);

    // Buscar habitaciones por sector
    List<HabitacionPaciente> findBySectorIgnoreCase(String sector);

    // Contar habitaciones por piso
    Long countBySectorIgnoreCase(String piso);
    List<HabitacionPaciente> findByObservacionInternaIsNull();

    List<HabitacionPaciente> findByObservacionInternaIsNotNull();
}
