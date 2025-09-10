package com.imb2025.smedico.service;

import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.entity.ObraSocial;

import java.util.List;

/**
 * Interfaz del servicio de negocio para la entidad ObraSocial.
 */
public interface IObraSocialService {

    /**
     * Obtiene todas las obras sociales registradas.
     */
    List<ObraSocial> findAll();

    /**
     * Crea una nueva obra social.
     * @param obraSocial entidad a crear
     * @return la entidad creada
     * @throws Exception si ocurre algún error durante la creación
     */
    ObraSocial create(ObraSocial obraSocial) throws Exception;

    /**
     * Actualiza una obra social existente.
     * @param id ID de la obra social a actualizar
     * @param obraSocial datos nuevos
     * @return la entidad actualizada
     * @throws Exception si ocurre un error durante la actualización
     */
    ObraSocial update(Long id, ObraSocial obraSocial) throws Exception;

    /**
     * Busca una obra social por ID.
     */
    ObraSocial findById(Long id);

    /**
     * Verifica si existe una obra social por ID.
     */
    boolean existsById(Long id);

    /**
     * Elimina una obra social por ID.
     */
    void deleteById(Long id);

    /**
     * Convierte un DTO en una entidad ObraSocial.
     * @param obraSocialRequestDto DTO de entrada
     * @return entidad construida
     * @throws Exception si los datos son inválidos
     */
    ObraSocial fromDto(ObraSocialRequestDto obraSocialRequestDto) throws Exception;
}
