package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb2025.smedico.entity.ObraSocial;
import java.util.Optional;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

    // Cambio: agregado método para buscar por nombre, útil en validaciones de negocio
    Optional<ObraSocial> findByNombre(String nombre);
    boolean existsByNombre(String nombre);

}

