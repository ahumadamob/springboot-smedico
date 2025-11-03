package com.imb2025.smedico.dto.response;

public class MedicamentoResponseDto {
	
    private Long id;
    private Long version;
    private String nombre;
    private String presentacion;
    private String dosisSugerida;
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
    
    

}
