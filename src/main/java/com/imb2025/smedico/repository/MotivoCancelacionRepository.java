package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.MotivoCancelacion;

public interface MotivoCancelacionRepository extends JpaRepository<MotivoCancelacion, Long> {

	 List<MotivoCancelacion> findByNombreContainingIgnoreCase(String nombre);
	 
	 long countByDescripcion(String descripcion);
}
