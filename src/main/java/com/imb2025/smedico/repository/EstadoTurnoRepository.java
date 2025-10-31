package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, Long> {

    
    // TP07: Métodos Mágicos (Query Methods)
    

    /**
      Busca EstadosTurno cuyo nombre contiene la cadena dada, ignorando mayúsculas/minúsculas.
      Esto se usará para el filtro de búsqueda.
      Ejemplo: findByNombreContainingIgnoreCase("canc") encuentra "Cancelado" y "CANCELACION".
      @param nombreFiltro La cadena a buscar.
      @return Una lista de EstadosTurno que cumplen con el criterio.
     */
    List<EstadoTurno> findByNombreContainingIgnoreCase(String nombreFiltro);

    /**
      Cuenta la cantidad de EstadosTurno que tienen un nombre exacto (case-insensitive).
    Esto se usará para la validación de unicidad o conteo.
      Ejemplo: countByNombreIgnoreCase("pendiente") devuelve el número de veces que existe.
      @param nombre El nombre exacto a contar.
      @return El número de coincidencias.
     */
    long countByNombreIgnoreCase(String nombre);
}
