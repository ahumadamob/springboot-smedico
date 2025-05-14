package com.imb2025.smedico.RepositoryET;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.imb2025.smedico.EntityET.EstadoTurnoE;

@Repository
public interface EstadoTurnoR extends JpaRepository<EstadoTurnoE, Long>{ 


}
	
