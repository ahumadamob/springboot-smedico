package com.imb2025.smedico.dto;

public class DireccionPacienteRequestDTO {

    private String calle;
    private Long numero;
    private String localidad;
    private String provincia;
    private String ccpp;

    public DireccionPacienteRequestDTO() {
    }

    public DireccionPacienteRequestDTO(String calle, Long numero, String localidad, String provincia, String ccpp) {
        this.calle = calle;
        this.numero = numero;
        this.localidad = localidad;
        this.provincia = provincia;
        this.ccpp = ccpp;
    }

    // Getters y Setters

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public Long getNumero() {
        return numero;
    }

    public void setNumero(Long numero) {
        this.numero = numero;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public String getCcpp() {
        return ccpp;
    }

    public void setCcpp(String ccpp) {
        this.ccpp = ccpp;
    }
}

