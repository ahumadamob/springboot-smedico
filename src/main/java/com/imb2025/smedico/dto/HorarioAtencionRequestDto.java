package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class HorarioAtencionRequestDto {

    @NotNull(message = "El ID del médico no puede ser nulo")
    private Long medicoId;

    @NotBlank(message = "El día de la semana no puede estar vacío")
    private String diaSemana;

    @NotBlank(message = "La hora de inicio no puede estar vacía")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "El formato de la hora de inicio debe ser HH:mm")
    private String horaInicio;

    @NotBlank(message = "La hora de fin no puede estar vacía")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "El formato de la hora de fin debe ser HH:mm")
    private String horaFin;

    public HorarioAtencionRequestDto() {}

    public HorarioAtencionRequestDto(Long medicoId, String diaSemana, String horaInicio, String horaFin) {
        this.medicoId = medicoId;
        this.diaSemana = diaSemana;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
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
