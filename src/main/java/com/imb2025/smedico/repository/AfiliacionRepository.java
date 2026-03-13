package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Afiliacion;

import java.util.List;
import java.util.Optional;

public interface AfiliacionRepository extends JpaRepository<Afiliacion, Long> {
    List<Afiliacion> findByIdGreaterThan(Long idMin);
    long countByIdGreaterThan(Long idMin);
    
    List<Afiliacion> findByDescripcionCortaContainingIgnoreCase(String texto);
    Optional<Afiliacion> findByDescripcionCortaIgnoreCase(String descripcionCorta);
    
}

