package com.imb2025.smedico.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Turno;

public interface TurnoRepository extends JpaRepository<Turno, Long> {

List<Turno> findByFecha(LocalDate fecha); //Enlista turnos por fecha

long countByFecha(LocalDate fecha); //Cuenta turnos en uan fecha específica
}
