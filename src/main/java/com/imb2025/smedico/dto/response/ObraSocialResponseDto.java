package com.imb2025.smedico.dto.response;

public class ObraSocialResponseDto {

    private Long id;
    private String nombre;
    private String telefono;
    private String direccion;
    private String cobertura;

    // 🔹 Nuevo campo: indica si la obra social está activa
    private boolean activa;

    // 🔹 Nuevo campo: versión del registro (placeholder, se puede setear manualmente en el mapper)
    private int version;

    // Getters y Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCobertura() {
        return cobertura;
    }
    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public boolean isActiva() {
        return activa;
    }
    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    public int getVersion() {
        return version;
    }
    public void setVersion(int version) {
        this.version = version;
    }
}



