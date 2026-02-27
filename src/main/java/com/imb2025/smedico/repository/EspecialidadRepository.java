package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Especialidad;

public interface EspecialidadRepository extends JpaRepository<Especialidad, Long> {

	List<Especialidad> findByNombre(String nombre); 
	
	long countByDescripcion(String descripcion);
	
	List<Especialidad> findByAtributoBooleanoTrue();
	List<Especialidad> findByAtributoBooleanoFalse();
	
	

	List<Especialidad> findByAliasContainingIgnoreCase(String alias);

}
