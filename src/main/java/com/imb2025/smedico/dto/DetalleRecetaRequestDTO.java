package com.imb2025.smedico.dto;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.entity.Medicamento;

public class DetalleRecetaRequestDTO {

    private Long recetaId;
    private Long medicamentoId;
    private String dosis;
    private String frecuencia;

    public DetalleRecetaRequestDTO() {}

    // Getters y setters

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

    // Método para convertir DTO a entidad DetalleReceta
    public DetalleReceta toEntity() {
        DetalleReceta detalle = new DetalleReceta();

        Receta receta = new Receta();
        receta.setId(this.recetaId);

        Medicamento medicamento = new Medicamento();
        medicamento.setId(this.medicamentoId);

        detalle.setReceta(receta);
        detalle.setMedicamento(medicamento);
        detalle.setDosis(this.dosis);
        detalle.setFrecuencia(this.frecuencia);

        return detalle;
    }
}

