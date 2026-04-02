package com.imb2025.smedico.service;


import com.imb2025.smedico.dto.request.OrdenEstudioRequestDto;
import com.imb2025.smedico.dto.response.OrdenEstudioResponseDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;

import utilities.EstadoOrden;

import java.time.LocalDate;
import java.util.List;

public interface IOrdenEstudioService {

    // Devuelve todas las órdenes de estudio
    List<OrdenEstudio> findAll();

    List<OrdenEstudio> findByFecha(LocalDate fecha);
    long countByMedico(Medico medico);  
  
    // Crea una nueva orden de estudio
    OrdenEstudio create(OrdenEstudio ordenEstudio);
     
    // Actualiza una orden existente por ID
    OrdenEstudioResponseDto update(Long id, OrdenEstudioRequestDto dto);

    // Busca una orden por su ID
    OrdenEstudio findById(Long id);

    // Verifica si existe una orden por ID
    boolean existsById(Long id);

    // Elimina una orden por ID
    void deleteById(Long id);
    
	List<OrdenEstudio> findByAutorizadoTrue();
	List<OrdenEstudio> findByAutorizadoFalse();
	
	boolean existsByCodigoOrdenIgnoreCase(String codigoOrden);

	List<OrdenEstudio>findByFechaVigenciaGreaterThanEqual(LocalDate fechaVigencia);
	List<OrdenEstudio> findByFechaVigenciaLessThan(LocalDate fechaVigencia);

    long countByEstado(EstadoOrden estadoOrden);

	OrdenEstudioResponseDto findByPrioridad(Long id, Integer prioridad) throws Exception;

	
	

    public List<OrdenEstudio> mostrarPrioridadMayorQue(int prioridad);

    public List<OrdenEstudio> mostrarPrioridadMenorQue(int prioridad);

}
