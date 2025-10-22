package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.OrdenEstudio;



public class ResultadoEstudioResponseDto {


	private OrdenEstudio ordenEstudio;
	private Estudio estudio;
	private Long resultado;
    private LocalDate fechaCarga;
    private String observaciones;
    
    private Long id;    
    private Long version;
    private Boolean atributoBooleano;
	
    
    
	
	public OrdenEstudio getOrdenEstudio() {
		return ordenEstudio;
	}
	public void setOrdenEstudio(OrdenEstudio ordenEstudio) {
		this.ordenEstudio = ordenEstudio;
	}
	public Estudio getEstudio() {
		return estudio;
	}
	public void setEstudio(Estudio estudio) {
		this.estudio = estudio;
	}
	public Long getResultado() {
		return resultado;
	}
	public void setResultado(Long resultado) {
		this.resultado = resultado;
	}
	public LocalDate getFechaCarga() {
		return fechaCarga;
	}
	public void setFechaCarga(LocalDate fechaCarga) {
		this.fechaCarga = fechaCarga;
	}
	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
	}
	public Boolean getAtributoBooleano() {
		return atributoBooleano;
	}
	public void setAtributoBooleano(Boolean atributoBooleano) {
		this.atributoBooleano = atributoBooleano;
	}
    
    
    
    
}
