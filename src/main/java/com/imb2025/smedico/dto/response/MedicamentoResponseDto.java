package com.imb2025.smedico.dto.response;

public class MedicamentoResponseDto {

    private Long id;
    private String nombre;
    private String presentacion;
    private String dosisSugerida;
    private Boolean esActivo; // El nuevo atributo

    // Constructores, Getters y Setters...

    public MedicamentoResponseDto() {}

    public MedicamentoResponseDto(Long id, String nombre, String presentacion, String dosisSugerida, Boolean esActivo) {
        this.id = id;
        this.nombre = nombre;
        this.presentacion = presentacion;
        this.dosisSugerida = dosisSugerida;
        this.esActivo = esActivo;
    }

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
    public String getPresentacion() {
        return presentacion;
    }
    public void setPresentacion(String presentacion) {
        this.presentacion = presentacion;
    }
    public String getDosisSugerida() {
        return dosisSugerida;
    }
    public void setDosisSugerida(String dosisSugerida) {
        this.dosisSugerida = dosisSugerida;
    }
    public Boolean getEsActivo() {
        return esActivo;
    }
    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }
}