package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb2025.smedico.entity.ObraSocial;
import java.util.Optional;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

   
    Optional<ObraSocial> findByNombre(String nombre);
    Optional<ObraSocial> findByCodigoReferenciaIgnoreCase(String codigoReferencia);
    Boolean existByCodigoReferenciaIgnoreCase(String codigoReferencia);
    

    boolean existsByNombre(String nombre);

    long countByCobertura(String cobertura);
}


