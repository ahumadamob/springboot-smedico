package com.imb2025.smedico.dto.response;

import com.imb2025.smedico.entity.Paciente;

public class PacienteResponseDto {

    private Long id; 
    private Long version;
    private String nombre;
    private String apellido;
    private String dni;
    private String email;
    private boolean activo;
    
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
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getDni() {
		return dni;
	}
	public void setDni(String dni) {
		this.dni = dni;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public boolean isActivo() {
        return activo;
    }
	public void setActivo(boolean activo) { 
	    this.activo = activo;
	}
}
