package com.imb2025.smedico.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Consultorio;

public interface ConsultorioRepository extends JpaRepository<Consultorio, Long> {

	List<Consultorio> findByUbicacion(String ubicacion);

	Consultorio findByNombre(String nombre);
	
	Optional<Consultorio> findByIdentificadorLegibleIgnoreCase(String identificadorLegible);
}
