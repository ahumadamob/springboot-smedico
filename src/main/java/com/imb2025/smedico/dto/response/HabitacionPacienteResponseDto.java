package com.imb2025.smedico.dto.response;

public class HabitacionPacienteResponseDto {

    private Long id;
    private Integer numeroHabitacion;
    private Integer piso;
    private String sector;
    private Integer camasDisponibles;
    private String descripcion;
    private Integer version;

    public HabitacionPacienteResponseDto() {}

    public HabitacionPacienteResponseDto(Long id, Integer numeroHabitacion, Integer piso, String sector,
                                         Integer camasDisponibles, String descripcion, Integer version) {
        this.id = id;
        this.numeroHabitacion = numeroHabitacion;
        this.piso = piso;
        this.sector = sector;
        this.camasDisponibles = camasDisponibles;
        this.descripcion = descripcion;
        this.version = version;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumeroHabitacion() {
        return numeroHabitacion;
    }

    public void setNumeroHabitacion(Integer numeroHabitacion) {
        this.numeroHabitacion = numeroHabitacion;
    }

    public Integer getPiso() {
        return piso;
    }

    public void setPiso(Integer piso) {
        this.piso = piso;
    }

    public String getSector() {
        return sector;
    }

    public void setSector(String sector) {
        this.sector = sector;
    }

    public Integer getCamasDisponibles() {
        return camasDisponibles;
    }

    public void setCamasDisponibles(Integer camasDisponibles) {
        this.camasDisponibles = camasDisponibles;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}
