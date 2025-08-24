package com.imb2025.smedico.dto;

public class ConsultorioRequestDto {

    private String nombre;
    private String ubicacion;
    private int piso;

    public ConsultorioRequestDto() {}

    public ConsultorioRequestDto(String nombre, String ubicacion, int piso) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.piso = piso;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }
}
