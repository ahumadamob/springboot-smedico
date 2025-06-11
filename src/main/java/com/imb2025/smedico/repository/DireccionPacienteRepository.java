package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.DireccionPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DireccionPacienteRepository extends JpaRepository<DireccionPaciente, Long> {
}

