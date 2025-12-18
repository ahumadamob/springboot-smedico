package com.imb2025.smedico.repository;

import java.time.LocalDate; 
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;

import utilities.EstadoOrden;

/**
 * Repository para la entidad OrdenEstudio.
 * Proporciona métodos CRUD básicos a través de JpaRepository.
 */
@Repository
public interface OrdenEstudioRepository extends JpaRepository<OrdenEstudio, Long> {
 
    
	List<OrdenEstudio> findByFecha(LocalDate fecha);

	long countByMedico(Medico medico);
	
	List<OrdenEstudio> findByAutorizadoTrue();
	List<OrdenEstudio> findByAutorizadoFalse();
	
	boolean existsByCodigoOrdenIgnoreCase(String codigoOrden);

	List<OrdenEstudio>findByFechaVigenciaGreaterThanEqual(LocalDate fechaVigencia);
	List<OrdenEstudio> findByFechaVigenciaLessThan(LocalDate fechaVigencia);

    long countByEstadoOrden(EstadoOrden estadoOrden);
    
	List<OrdenEstudio> findByPrioridadGreaterThanEqual(int prioridad);

	List<OrdenEstudio> findByPrioridadLessThanEqual(int prioridad);
}
