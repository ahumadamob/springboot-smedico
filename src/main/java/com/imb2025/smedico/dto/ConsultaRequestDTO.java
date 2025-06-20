package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class ConsultaRequestDTO {
    private LocalDate fecha;
    private String turnoId;
    private int duracionMin;
    private String comentarios;
    private Long pacienteId;

    public void  setPacienteId(Long pacienteId) {
    	this.pacienteId = pacienteId;
    }
    public Long getPacienteId() {
    	return pacienteId;
    }
    
    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(String turnoId) {
        this.turnoId = turnoId;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    @Override
    public String toString() {
        return "ConsultaRequestDTO{" +
                "fecha=" + fecha +
                ", turnoId='" + turnoId + '\'' +
                ", duracionMin=" + duracionMin +
                ", comentarios='" + comentarios + '\'' +
                '}';
    }
}
