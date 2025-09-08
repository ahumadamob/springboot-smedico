package com.imb2025.smedico.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
//import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class AsistenteRequestDto {

    @NotBlank(message = "El apellido no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String apellido;
    
    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
	private String nombre;
    
    @NotBlank(message = "El email no puede estar vacío")
    @Email(message = "Debe ser un correo válido")
    private String email;
    
    @Positive(message = "El DNI debe ser un valor numérico positivo")
    private Long telefono;
    
    @Positive(message = "El DNI debe ser un valor numérico positivo")
    private Long dni;
	
    public AsistenteRequestDto() {
	}

	public AsistenteRequestDto(String apellido, String nombre, String email, Long telefono, Long dni) {
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

	public Long getTelefono() {
		return telefono;
	}

	public void setTelefono(Long telefono) {
		this.telefono = telefono;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}
}

