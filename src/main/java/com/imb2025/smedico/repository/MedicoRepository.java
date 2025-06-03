package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
	
	

}
