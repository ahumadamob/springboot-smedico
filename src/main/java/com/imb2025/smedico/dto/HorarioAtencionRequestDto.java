package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive; 
import jakarta.validation.constraints.Size;     

public class HorarioAtencionRequestDto {

    @NotNull(message = "El ID del médico no puede ser nulo")
    @Positive(message = "El ID del médico debe ser un número positivo") // Added
    private Long medicoId;

    @NotBlank(message = "El día de la semana no puede estar vacío")
    @Size(min = 5, max = 9, message = "El día de la semana debe tener entre 5 y 9 caracteres") // Added
    @Pattern(regexp = "Lunes|Martes|Miércoles|Jueves|Viernes|Sábado|Domingo", message = "Día de la semana inválido") // Added
    private String diaSemana;

    @NotBlank(message = "La hora de inicio no puede estar vacía")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$"

   , message = "El formato de la hora de inicio debe ser HH:mm")
    private String horaInicio;

    @NotBlank(message = "La hora de fin no puede estar vacía")
    @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$"

    , message = "El formato de la hora de fin debe ser HH:mm")
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
