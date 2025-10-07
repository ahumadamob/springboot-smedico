package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Medico;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

    
    List<Medico> findByApellido(String apellido);

    
    Long countByEspecialidad_Nombre(String nombreEspecialidad);
}

