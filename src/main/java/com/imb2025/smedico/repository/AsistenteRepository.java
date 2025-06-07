package com.imb2025.smedico.repository;
//Repositorio
import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Asistente;

public interface AsistenteRepository extends JpaRepository<Asistente, Long>{
	
}
