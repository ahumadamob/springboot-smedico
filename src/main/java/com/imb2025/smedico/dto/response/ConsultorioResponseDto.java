package com.imb2025.smedico.dto.response;

import java.time.LocalDate;

public class ConsultorioResponseDto {

	private String nombre;
	private String ubicacion;
	private int piso;
	private Long version;
	private String identificadorLegible;
    private LocalDate fechaArchivado;
       
	public LocalDate getFechaArchivado() {
		return fechaArchivado;
	}
	public void setFechaArchivado(LocalDate fechaArchivado) {
		this.fechaArchivado = fechaArchivado;
	}
	public void setIdentificadorLegible(String identificadorLegible) {
		this.identificadorLegible = identificadorLegible;
	}
	public String getIdentificadorLegible() {
		return identificadorLegible;
	}
	public void setIdentificador_legible(String identificadorLegible) {
		this.identificadorLegible = identificadorLegible;
	}
	public Long getVersion() {
		return version;
	}
	public void setVersion(Long version) {
		this.version = version;
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
