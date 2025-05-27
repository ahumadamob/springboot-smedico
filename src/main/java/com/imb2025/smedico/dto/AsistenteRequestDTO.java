package com.imb2025.smedico.dto;
//Request
public class AsistenteRequestDTO {
	
	 private String nombre;
	 private String email;
	 private Long telefono;
	 private String dni;

	    // Getters y Setters
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }

	    public String getEmail() {
	        return email;
	    }

	    public void setEmail(String email) {
	        this.email = email;
	    }

	    public Long getTelefono() {
	        return telefono;
	    }

	    public void setTelefono(Long telefono) {
	        this.telefono = telefono;
	    }

	    public String getDni() {
	        return dni;
	    }

	    public void setDni(String dni) {
	        this.dni = dni;
	    }
}
