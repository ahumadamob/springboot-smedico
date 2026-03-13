package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.Diagnostico;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
	 
	List<Diagnostico> findByFechaDiagnostico(LocalDate fechaDiagnostico);
	
	long countByFechaDiagnostico(LocalDate fechaDiagnostico);
	
    Optional<Diagnostico> findByCodigoReferenciaIgnoreCase(String codigoReferencia);
    boolean existsByCodigoReferenciaIgnoreCase(String codigoReferencia);

	boolean existsByCodigoReferenciaIgnoreCaseAndIdNot(String codigoNormalizado, Long id);
	
}
