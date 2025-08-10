package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Diagnostico;

public interface DiagnosticoRepository extends JpaRepository<Diagnostico, Long> {
}
