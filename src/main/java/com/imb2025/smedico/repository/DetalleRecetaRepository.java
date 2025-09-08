package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.DetalleReceta;

/**
 * Repositorio JPA para la entidad DetalleReceta.
 * Proporciona métodos CRUD estándar y permite agregar consultas personalizadas si se requiere.
 */
public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {
}
