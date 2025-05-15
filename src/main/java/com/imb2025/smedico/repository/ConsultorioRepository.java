package com.imb2025.smedico.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Consultorio;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {
	
	List<Consultorio> findByNombre(String nombre);
    void deleteByNombre(String nombre);
	
}
