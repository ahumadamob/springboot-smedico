package com.imb2025.smedico.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.SignosVitales;

public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Long>{

	List<SignosVitales> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
	
	Long countByConsulta(Consulta consulta);
	
	List<SignosVitales> findByPublicadoTrue();
	List<SignosVitales> findByPublicadoFalse();

}