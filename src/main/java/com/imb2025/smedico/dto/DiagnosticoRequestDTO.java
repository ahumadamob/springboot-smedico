package com.imb2025.smedico.dto;

import java.time.LocalDate;

public class DiagnosticoRequestDTO {
	
	    private Long consultaId;
	    private String descripcion;
	    private LocalDate fechaDiagnostico;

	    // Getters y setters
	    public Long getConsultaId() {
	        return consultaId;
	    }

	    public void setConsultaId(Long consultaId) {
	        this.consultaId = consultaId;
	    }

	    public String getDescripcion() {
	        return descripcion;
	    }

	    public void setDescripcion(String descripcion) {
	        this.descripcion = descripcion;
	    }

	    public LocalDate getFechaDiagnostico() {
	        return fechaDiagnostico;
	    }

	    public void setFechaDiagnostico(LocalDate fechaDiagnostico) {
	        this.fechaDiagnostico = fechaDiagnostico;
	    }
	    
}
