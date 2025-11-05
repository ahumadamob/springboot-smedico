package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.Diagnostico;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {

    // TP7 que ya usaba
    List<Diagnostico> findByFechaDiagnostico(LocalDate fechaDiagnostico);
    long countByFechaDiagnostico(LocalDate fechaDiagnostico);

    // Ejercicio 2 (duplicado)
    Optional<Diagnostico> findByIdentificadorLegibleIgnoreCase(String identificadorLegible);
    boolean existsByIdentificadorLegibleIgnoreCase(String identificadorLegible);

    // Ejercicio 3 (vigentes / vencidos)
    List<Diagnostico> findByFechaVigenciaGreaterThanEqual(LocalDate fecha);
    List<Diagnostico> findByFechaVigenciaLessThan(LocalDate fecha);
}

