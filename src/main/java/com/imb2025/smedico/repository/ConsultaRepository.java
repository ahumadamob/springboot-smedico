package com.imb2025.smedico.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Consulta;

import java.time.LocalDate;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {
    boolean existsByTurno_Id(Long turnoId);
    boolean existsByTurno_IdAndIdNot(Long turnoId, Long id);
    Page<Consulta> findByFechaBetween(LocalDate desde, LocalDate hasta, Pageable pageable);
    long countByTurno_Paciente_Id(Long pacienteId);
}

