package com.imb2025.smedico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "receta_medicamento")
public class RecetaMedicamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recetaId;
    private Long medicamentoId;
    private String dosis;
    private String frecuencia;

    // Constructores
    public RecetaMedicamento() {
    }

    public RecetaMedicamento(Long recetaId, Long medicamentoId, String dosis, String frecuencia) {
        this.recetaId = recetaId;
        this.medicamentoId = medicamentoId;
        this.dosis = dosis;
        this.frecuencia = frecuencia;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getRecetaId() {
        return recetaId;
    }

    public void setRecetaId(Long recetaId) {
        this.recetaId = recetaId;
    }

    public Long getMedicamentoId() {
        return medicamentoId;
    }

    public void setMedicamentoId(Long medicamentoId) {
        this.medicamentoId = medicamentoId;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }
}
