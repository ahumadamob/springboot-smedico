package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.entity.Receta;

import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleRecetaRepository extends JpaRepository<DetalleReceta, Long> {


	public interface RecetaRepository extends JpaRepository<Receta, Long> {
	}

}
