package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.HabitacionPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HabitacionPacienteRepository extends JpaRepository<HabitacionPaciente, Long> {
}
