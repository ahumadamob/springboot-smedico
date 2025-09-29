package com.imb2025.smedico.repository;

import java.time.LocalDate;
import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;

public interface OrdenEstudioRepository extends JpaRepository<OrdenEstudio, Long> {
 
    
	List<OrdenEstudio> findByFecha(LocalDate fecha);

	long countByMedico(Medico medico);
	
	
}

