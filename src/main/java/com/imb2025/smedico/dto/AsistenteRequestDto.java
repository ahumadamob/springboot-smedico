package com.imb2025.smedico.dto;

public class AsistenteRequestDto {

    private String apellido;
	private String nombre;
    private String email;
    private String telefono;
    private String dni;
	
    public AsistenteRequestDto() {
	}

	public AsistenteRequestDto(String apellido, String nombre, String email, String telefono, String dni) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.email = email;
		this.telefono = telefono;
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

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

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}
}

