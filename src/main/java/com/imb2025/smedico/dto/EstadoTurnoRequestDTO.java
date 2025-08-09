package com.imb2025.smedico.dto;

public class EstadoTurnoRequestDTO {
    private String nombre;

    public EstadoTurnoRequestDTO() {
    }

    public EstadoTurnoRequestDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
