package com.imb2025.smedico.dto;

public class HorarioAtencionResponseDTO {

    private Long id;
    private Long medicoId;
    private String diaSemana;
    private String horaInicio;
    private String horaFin;

    public HorarioAtencionResponseDTO() {
    }


    public HorarioAtencionResponseDTO(Long id, Long medicoId, String diaSemana, String horaInicio, String horaFin) {
        this.id = id;
        this.medicoId = medicoId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMedicoId() {
        return medicoId;
    }

    public void setMedicoId(Long medicoId) {
        this.medicoId = medicoId;
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
}