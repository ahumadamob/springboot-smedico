package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    // Buscar pacientes por apellido
    List<Paciente> findByApellido(String apellido);

    // Contar pacientes por email
    long countByEmail(String email);
}

