package com.imb2025.smedico.dto.response;

public class HorarioAtencionResponseDto {

    private Long id;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;
    private Integer version;

    // Constructor vacío
    public HorarioAtencionResponseDto() {
    }

    // Constructor con parámetros
    public HorarioAtencionResponseDto(Long id, String diaSemana, String horaInicio, String horaFin, Integer version) {
        this.id = id;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.version = version;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDiaSemana() {
        return diaSemana;
    }

    public void setDiaSemana(String diaSemana) {
        this.diaSemana = diaSemana;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }
}