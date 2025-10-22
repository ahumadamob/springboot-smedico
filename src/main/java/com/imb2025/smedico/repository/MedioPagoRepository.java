package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.Estado;

public interface MedioPagoRepository extends JpaRepository<MedioPago, Long> {
	
	List<MedioPago> findByTipo(MedioPago.TipoPago tipo);

    Long countByNombre(String nombre);

	Long countByEstado(Estado estado);//a



}
