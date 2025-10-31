package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.dto.ObraSocialResponseDto;

import java.util.List;

/**
 * Interfaz del servicio de negocio para la entidad ObraSocial.
 */
public interface IObraSocialService {

    /**
     * Obtiene todas las obras sociales registradas.
     */
    List<ObraSocialResponseDto> findAll();

    /**
     * Crea una nueva obra social.
     * @param obraSocial datos recibidos desde la petición HTTP
     * @return DTO con la obra social creada
     */
    ObraSocialResponseDto create(ObraSocialRequestDto obraSocial);

    /**
     * Actualiza una obra social existente.
     * @param id ID de la obra social a actualizar
     * @param obraSocial datos nuevos recibidos desde la petición HTTP
     * @return DTO con la obra social actualizada
     */
    ObraSocialResponseDto update(Long id, ObraSocialRequestDto obraSocial);

    /**
     * Busca una obra social por ID.
     */
    ObraSocialResponseDto findById(Long id);

    /**
     * Verifica si existe una obra social por ID.
     */
    boolean existsById(Long id);
    
    long countByCobertura(String cobertura);

    /**
     * Elimina una obra social por ID.
     */
    void deleteById(Long id);
    ObraSocialResponseDto findByNombre(String nombre);


}
