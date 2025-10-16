package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.request.EstadoTurnoRequestDto;
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

    // Mapeo (LÓGICA ELIMINADA DEL CONTRATO DEL SERVICIO - PUNTO 4.1)
    // EstadoTurno fromDto(EstadoTurnoRequestDto dto); 

    

    /**
      Filtra la lista de EstadosTurno por subcadena en el nombre 
      @param nombreFiltro La subcadena a buscar.
      @return Lista de EstadosTurno filtrados.
     */
    List<EstadoTurno> findByNombreContaining(String nombreFiltro);

    /**
      Cuenta la cantidad de EstadosTurno con un nombre específico 
     
     */
    long countByNombre(String nombre);
}
