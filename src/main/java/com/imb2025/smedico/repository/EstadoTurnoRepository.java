package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, Long> {

    // -------------------------------------------------------------------------
    // EJERCICIO 1: Métodos Mágicos de Filtro Booleano (esFinal)
    // -------------------------------------------------------------------------

    /**
     * Busca y devuelve todos los EstadosTurno donde el atributo esFinal es TRUE.
     * Esto se usará para el endpoint /finales.
     * Ejemplo: findByEsFinalTrue()
     * @return Una lista de EstadosTurno donde esFinal = TRUE.
     */
    List<EstadoTurno> findByEsFinalTrue();

    /**
     * Busca y devuelve todos los EstadosTurno donde el atributo esFinal es FALSE.
     * Esto se usará para el endpoint /pendientes.
     * Ejemplo: findByEsFinalFalse()
     * @return Una lista de EstadosTurno donde esFinal = FALSE.
     */
    List<EstadoTurno> findByEsFinalFalse();

    // -------------------------------------------------------------------------
    // TP07: Métodos Mágicos de Filtro General y Conteo
    // -------------------------------------------------------------------------

    /**
     * Busca EstadosTurno cuyo nombre contiene la cadena dada, ignorando mayúsculas/minúsculas.
     * Esto se usará para el filtro de búsqueda.
     * @param nombreFiltro La cadena a buscar.
     * @return Una lista de EstadosTurno que cumplen con el criterio.
     */
    List<EstadoTurno> findByNombreContainingIgnoreCase(String nombreFiltro);

    /**
     * Cuenta la cantidad de EstadosTurno que tienen un nombre exacto (case-insensitive).
     * Esto se usará para la validación de unicidad o conteo.
     * @param nombre El nombre exacto a contar.
     * @return El número de coincidencias.
     */
    long countByNombreIgnoreCase(String nombre);
    
    // Método para la lógica de unicidad o búsqueda exacta (usado a menudo en servicios)
    Optional<EstadoTurno> findByNombreIgnoreCase(String nombre);
}