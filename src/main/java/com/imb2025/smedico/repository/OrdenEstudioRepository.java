package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb2025.smedico.entity.OrdenEstudio;

/**
 * Repository para la entidad OrdenEstudio.
 * Proporciona métodos CRUD básicos a través de JpaRepository.
 */
@Repository
public interface OrdenEstudioRepository extends JpaRepository<OrdenEstudio, Long> {
    // No se agregan métodos adicionales, se usan los heredados de JpaRepository
}
