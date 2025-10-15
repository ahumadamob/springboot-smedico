package com.imb2025.calificaciones.mapper;

import com.imb2025.calificaciones.dto.request.NivelMateriaRequestDto;
import com.imb2025.calificaciones.dto.response.NivelMateriaResponseDto;
import com.imb2025.calificaciones.entity.NivelMateria;

/**
 * Conversión explícita entre entidades {@link NivelMateria} y sus DTO asociados.
 */
public final class NivelMateriaMapper {

    private NivelMateriaMapper() {
    }

    public static NivelMateria toEntity(NivelMateriaRequestDto requestDto) {
        if (requestDto == null) {
            return null;
        }
        NivelMateria entity = new NivelMateria();
        entity.setNombre(requestDto.getNombre());
        entity.setDescripcion(requestDto.getDescripcion());
        return entity;
    }

    public static void updateEntity(NivelMateria entity, NivelMateriaRequestDto requestDto) {
        if (entity == null || requestDto == null) {
            return;
        }
        entity.setNombre(requestDto.getNombre());
        entity.setDescripcion(requestDto.getDescripcion());
    }

    public static NivelMateriaResponseDto toResponse(NivelMateria entity) {
        if (entity == null) {
            return null;
        }
        return new NivelMateriaResponseDto(
                entity.getId(),
                entity.getNombre(),
                entity.getDescripcion(),
                entity.getVersion());
    }
}
