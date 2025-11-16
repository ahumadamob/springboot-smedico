package com.imb2025.smedico.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

/**
 * DTO de respuesta para la entidad EstadoTurno. Cumple con los requisitos del TP08.
 * Incluye solo los campos de negocio, el campo 'esFinal' (Ejercicio 1) y el control de concurrencia (version).
 * Omite campos de auditoría como createdAt y updatedAt (requisito TP08).
 */
@Schema(description = "DTO de respuesta para el recurso EstadoTurno")
public class EstadoTurnoResponseDto {

    private Long id;
    
    @Schema(description = "Nombre descriptivo del estado del turno")
    private String nombre;
    
    @Schema(description = "Indica si el estado es final (true) o pendiente (false) - Ejercicio 1")
    private Boolean esFinal;
    
    // CORRECCIÓN: El tipo debe ser Long para coincidir con la Entidad (@Version Long)
    @Schema(description = "Versión para control de concurrencia optimista")
    private Long version; 

    // Constructor por defecto
    public EstadoTurnoResponseDto() {}

    /**
     * Constructor utilizado por el Mapper para convertir la entidad a DTO de respuesta.
     * @param id Identificador de la entidad.
     * @param nombre Nombre del estado.
     * @param esFinal Indicador de estado final.
     * @param version Versión de concurrencia.
     */
    public EstadoTurnoResponseDto(Long id, String nombre, Boolean esFinal, Long version) {
        this.id = id;
        this.nombre = nombre;
        this.esFinal = esFinal;
        this.version = version;
    }

    // --- Getters ---
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Boolean getEsFinal() {
        return esFinal;
    }
    
    public Long getVersion() {
        return version;
    }
    
    // --- Setters ---
    public void setId(Long id) {
        this.id = id;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public void setEsFinal(Boolean esFinal) {
        this.esFinal = esFinal;
    }
    
    public void setVersion(Long version) {
        this.version = version;
    }
}
