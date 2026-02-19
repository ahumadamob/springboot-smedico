package com.imb2025.smedico.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.imb2025.smedico.entity.Encuesta;

public interface EncuestaRepository extends JpaRepository<Encuesta, Long> {

    // Filtro por umbral de puntaje (findBy...)
    List<Encuesta> findByPuntajeGreaterThanEqual(int puntaje);

    // Conteo por relación a Consulta (countBy...)
    long countByConsulta_Id(Long consultaId);
}
