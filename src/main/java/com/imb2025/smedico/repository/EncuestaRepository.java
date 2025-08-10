package com.imb2025.smedico.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.EncuestaSatisfaccion;
public interface EncuestaRepository extends JpaRepository<EncuestaSatisfaccion, Long> {

}
