package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.imb2025.smedico.entity.ObraSocial;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

    Optional<ObraSocial> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

    
    Optional<ObraSocial> findByIdentificadorLegibleIgnoreCase(String identificadorLegible);

    long countByCobertura(String cobertura);
    

List<ObraSocial> findByFechaVigenciaGreaterThanEqual(LocalDate fecha);
List<ObraSocial> findByFechaVigenciaLessThan(LocalDate fecha);
}
