package com.imb2025.smedico.dto.response;

public class EspecialidadResponseDto {
	
    private Long id;       
    private Long version;	  
    private String nombre;
    private String descripcion;
    private Boolean atributoBuleano;
    private String alias;
   
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
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
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Boolean getAtributoBuleano() {
		return atributoBuleano;
	}
	public void setAtributoBuleano(Boolean atributoBuleano) {
		this.atributoBuleano = atributoBuleano;
	}

}
