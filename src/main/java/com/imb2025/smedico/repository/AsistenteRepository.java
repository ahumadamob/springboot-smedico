package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Asistente;

public interface AsistenteRepository extends JpaRepository<Asistente, Long> {

	
	List<Asistente> findByApellido(String apellido);
	
	long countByNombre(String nombre);
	
	List<Asistente> findBySeveridadGreaterThanEqual(Integer severidad);
	
	List<Asistente> findBySeveridadLessThanEqual(Integer severidad);
	
}
