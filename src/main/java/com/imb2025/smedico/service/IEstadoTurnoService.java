package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.exception.ResourceNotFoundException;

import java.util.List;

public interface IEstadoTurnoService {

    // Métodos CRUD existentes
    List<EstadoTurno> findAll();
    EstadoTurno findById(Long id) throws ResourceNotFoundException;
    boolean existsById(Long id);
    EstadoTurno create(EstadoTurno estadoTurno);
    EstadoTurno update(Long id, EstadoTurno estadoTurno) throws ResourceNotFoundException;
    void deleteById(Long id) throws ResourceNotFoundException;

    // Mapeo
    EstadoTurno fromDto(EstadoTurnoRequestDto dto);

  
    // TP07: Métodos de Filtro y Conteo
    

    /**
      Filtra la lista de EstadosTurno por subcadena en el nombre 
      @param nombreFiltro La subcadena a buscar.
      @return Lista de EstadosTurno filtrados.
     */
    List<EstadoTurno> findByNombreContaining(String nombreFiltro);

    /**
      Cuenta la cantidad de EstadosTurno con un nombre específico 
      @param nombre El nombre exacto a contar.
      @return El número de coincidencias.
     */
    long countByNombre(String nombre);
}
