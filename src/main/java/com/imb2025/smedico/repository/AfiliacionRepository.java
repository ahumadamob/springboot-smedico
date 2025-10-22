package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Afiliacion;

import java.util.List;

public interface AfiliacionRepository extends JpaRepository<Afiliacion, Long> {

    List<Afiliacion> findByIdGreaterThan(Long idMin);
    long countByIdGreaterThan(Long idMin);

    // Ejercicio 1: listados por atributo booleano
    List<Afiliacion> findByActivaTrue();
    List<Afiliacion> findByActivaFalse();
}
