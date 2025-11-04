package com.imb2025.smedico.repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.SignosVitales;

public interface SignosVitalesRepository extends JpaRepository<SignosVitales, Long>{

	List<SignosVitales> findByFechaHoraBetween(LocalDateTime inicio, LocalDateTime fin);
	
	Long countByConsulta(Consulta consulta);

	List<SignosVitales> findByFechaVigenciaGreaterThanEqual(LocalDate fecha);
	List<SignosVitales> findByFechaVigenciaLessThan(LocalDate fecha);
	Optional<SignosVitales> findByCodigoRegistroIgnoreCase(String codigoRegistro);


}