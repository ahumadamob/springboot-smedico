package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb2025.smedico.entity.ObraSocial;
import java.util.Optional;

@Repository
public interface ObraSocialRepository extends JpaRepository<ObraSocial, Long> {

    // Método para filtrar por nombre (findBy...)
    Optional<ObraSocial> findByNombre(String nombre);

    // Método para verificar existencia
    boolean existsByNombre(String nombre);

    // Método para contar cuántas obras sociales tienen un nombre específico (countBy...)
    long countByCobertura(String cobertura);
}


