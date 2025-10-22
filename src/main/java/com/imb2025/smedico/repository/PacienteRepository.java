package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
	List<Paciente> findByOrderByApellidoAscNombreAsc();
	List<Paciente> findByDni(String dni);
	List<Paciente> findByEmailEndingWith(String domain);
	List<Paciente> findByActivoTrue();
	List<Paciente> findByActivoFalse();
	Long countBy();
}

