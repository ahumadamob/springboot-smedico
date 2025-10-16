package com.imb2025.smedico.dto.response;

import com.imb2025.smedico.entity.EstadoTurno;
import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta para la entidad EstadoTurno.
 * Contiene los datos necesarios para el cliente, incluyendo el campo 'version'.
 */
@Schema(description = "DTO de respuesta para el recurso EstadoTurno")
public class EstadoTurnoResponseDto {

    private Long id;
    private String nombre;
    private Integer version; // Requisito del TP08: Campo de concurrencia

    public EstadoTurnoResponseDto() {
    }

    /**
     * Constructor utilizado por el Mapper para convertir la entidad a DTO de respuesta.
     * @param entidad La entidad EstadoTurno.
     */
    public EstadoTurnoResponseDto(EstadoTurno entidad) {
        this.id = entidad.getId();
        this.nombre = entidad.getNombre();
        // Asume que la entidad ya tiene getVersion()
        this.version = entidad.getVersion(); 
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getVersion() {
        return version;
    }
    
    // --- Setters (Opcionales, pero buena práctica si se usa Jackson para la deserialización) ---
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setVersion(Integer version) {
        this.version = version;
    }
}
