package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.EstadoTurnoRequestDto;
import com.imb2025.smedico.dto.response.EstadoTurnoResponseDto;
import com.imb2025.smedico.entity.EstadoTurno;
import org.springframework.stereotype.Component;

/**
 * Mapper para convertir entre Entidad y DTOs (Request/Response).
 * Centraliza toda la lógica de conversión para el TP08.
 */
@Component
public class EstadoTurnoMapper {

    /**
     * Convierte EstadoTurnoRequestDto a Entidad.
     * Usado para POST/PUT.
     * @param dto Datos recibidos en la solicitud.
     * @return La entidad lista para la persistencia.
     */
    public EstadoTurno toEntity(EstadoTurnoRequestDto dto) {
        EstadoTurno entidad = new EstadoTurno();
        
        // El ID es necesario si es una operación de actualización (PUT).
        entidad.setId(dto.getId()); 
        
        entidad.setNombre(dto.getNombre());
        
        // El campo 'version' lo gestiona JPA automáticamente.
        
        return entidad;
    }

    /**
     * Convierte Entidad a EstadoTurnoResponseDto.
     * Usado para todas las respuestas de éxito (GET, POST, PUT).
     * @param entidad La entidad procesada por el Service.
     * @return DTO de respuesta que incluye el ID y la versión.
     */
    public EstadoTurnoResponseDto toResponseDto(EstadoTurno entidad) {
        EstadoTurnoResponseDto responseDto = new EstadoTurnoResponseDto();
        
        responseDto.setId(entidad.getId());
        responseDto.setNombre(entidad.getNombre());
        
        // Incluye el campo 'version' para el control de concurrencia.
        responseDto.setVersion(entidad.getVersion()); 
        
        return responseDto;
    }
}