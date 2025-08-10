package com.imb2025.smedico.dto;

public class EstadoTurnoRequestDto {
    private String nombre;

    public EstadoTurnoRequestDto() {
    }

    public EstadoTurnoRequestDto(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
