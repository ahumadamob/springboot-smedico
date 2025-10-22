package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;
import com.imb2025.smedico.entity.ObraSocial;

/**
 * Mapper responsable de convertir entre DTOs y la entidad ObraSocial.
 */
public class ObraSocialMapper {

    /**
     * Convierte un ObraSocialRequestDto en una entidad ObraSocial.
     * @param dto ObraSocialRequestDto con los datos enviados desde el cliente.
     * @return ObraSocial lista para ser persistida.
     */
    public static ObraSocial fromDto(ObraSocialRequestDto dto) {
        if (dto == null) {
            return null;
        }
        ObraSocial entity = new ObraSocial();
        entity.setNombre(dto.getNombre());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
        entity.setCobertura(dto.getCobertura());
        entity.setActiva(dto.isActiva());
        return entity;
    }

    /**
     * Convierte una entidad ObraSocial en un ObraSocialResponseDto.
     * @param entity ObraSocial obtenida desde la base de datos.
     * @return ObraSocialResponseDto listo para enviarse al cliente.
     */
    public static ObraSocialResponseDto toResponseDto(ObraSocial entity) {
        if (entity == null) {
            return null;
        }
        ObraSocialResponseDto dto = new ObraSocialResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setTelefono(entity.getTelefono());
        dto.setDireccion(entity.getDireccion());
        dto.setCobertura(entity.getCobertura());
        dto.setActiva(entity.isActiva());

        // 🔹 Asignamos una versión por defecto (1), ya que no hay campo version en la entidad
        dto.setVersion(1);

        return dto;
    }
}

