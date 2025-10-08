package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Estudio;

public interface EstudioRepository extends JpaRepository<Estudio, Long> {
	List<Estudio> findAllByOrderByNombreAsc();
	List<Estudio> findByNombreContainingIgnoreCase(String nombre);
	long countByEspecialidad_Id(Long especialidadId);
}
