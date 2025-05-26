package com.imb2025.smedico.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.imb2025.smedico.entity.Estudio;

public interface EstudioRepository extends JpaRepository<Estudio,Long> {
    @EntityGraph(attributePaths = {
            "paciente", "medico", "especialidad", "obraSocial", "oredenEstudio", "resultadoEstudio"
        })
        List<Estudio> findAll();
	 
}
