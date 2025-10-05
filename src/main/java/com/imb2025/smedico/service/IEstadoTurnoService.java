package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.EstadoTurnoRequestDto;
import com.imb2025.smedico.entity.EstadoTurno;
import java.util.List;

public interface IEstadoTurnoService {
    public List<EstadoTurno> findAll();
    public EstadoTurno create(EstadoTurno estadoTurno);
    public EstadoTurno update(Long id, EstadoTurno estadoTurno);
    public EstadoTurno findById(Long id);
    public boolean existsById(Long id);
    public void deleteById(Long id);
    public EstadoTurno fromDto(EstadoTurnoRequestDto dto);
    
  
    // Consigna TP07 Métodos para usar los filtros del Repository
    
    
    /*
      Usa el método findBy... del Repository para filtrar por nombre.
      @param nombreFiltro Cadena a buscar.
      @return Lista de estados de turno que coinciden.
     */
    public List<EstadoTurno> findByNombreContaining(String nombreFiltro);
    
    /*
      Usa el método countBy... del Repository para contar coincidencias.
      @param nombre Nombre exacto a contar.
      @return Cantidad de registros.
     */
    public long countByNombre(String nombre);
}