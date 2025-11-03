package com.imb2025.smedico.dto.response;

import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.entity.Medicamento;

public class DetalleRecetaResponseDto {

    private Long id;
    private Long receta;            
    private Long medicamento; 
    private String dosis;
    private String frecuencia;
    private Long version;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getReceta() {
        return receta;
    }

    public void setReceta(Long long1) {
        this.receta = long1;
    }

    public Long getMedicamento() {
        return medicamento;
    }

    public void setMedicamento(Long long1) {
        this.medicamento = long1;
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

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long long1) {
        this.version = long1;
    }
}
