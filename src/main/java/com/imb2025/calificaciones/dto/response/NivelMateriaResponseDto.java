package com.imb2025.calificaciones.dto.response;

/**
 * Representa la vista resumida de un nivel de materia disponible en el sistema.
 */
public class NivelMateriaResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long version;

    public NivelMateriaResponseDto() {
    }

    public NivelMateriaResponseDto(Long id, String nombre, String descripcion, Long version) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.version = version;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }
}
