package com.imb2025.smedico.repository;

import com.imb2025.smedico.entity.EstadoTurno;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EstadoTurnoRepository extends JpaRepository<EstadoTurno, Long> {

   
    // TP07 Implementar dos métodos mágicos derivados por nombre
    

    /**
      1. Método findBy (Filtro)
      Busca todos los Estados de Turno cuyo nombre contenga la cadena de texto
    especificada, ignorando mayúsculas y minúsculas (ContainingIgnoreCase).
      @param nombre El fragmento de nombre a buscar.
      @return List<EstadoTurno> Lista de estados de turno que coinciden con el filtro.
     */
    List<EstadoTurno> findByNombreContainingIgnoreCase(String nombre);


    /**
      2. Método countBy (Contador)
      Cuenta cuántos Estados de Turno tienen un nombre que coincide exactamente
      con el valor proporcionado, ignorando mayúsculas y minúsculas (IgnoreCase).
      @param nombre El nombre exacto a contar.
      @return long El número de registros que cumplen la condición.
     */
    long countByNombreIgnoreCase(String nombre);
}
