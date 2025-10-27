package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class MedicoRequestDto {

	@NotBlank(message = "El Nombre es obligatorio")
	@Size(min = 2, max = 50, message = "El nombre debe contener entre 2 y 50 caracteres")
    private String nombre;
	
	@NotBlank(message = "El Apellido es obligatorio")
	@Size(min = 2, max = 50, message = "El apellido debe tener entre 2 y 50 caracteres")
    private String apellido;
	
	@NotBlank(message = "La matricula debe ser obligatoria")
	@Pattern(regexp = "^[A-Z0-9-]+$", message = "La matrícula debe ser alfanumérica y en mayúsculas")
	@Size(max = 20, message = "La matrícula no puede superar los 20 caracteres")
    private String matricula;
	
	@NotNull(message = "La especialidad debe ser obligatoria")
    private Long especialidadId;
	
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "El formato del Email no es valido")
    private String email;
    
    @NotBlank(message = "El teléfono es obligatorio")
    @Pattern(regexp = "^[0-9]{7,15}$", message = "El teléfono debe contener entre 7 y 15 dígitos")
    @Size(min = 7, max = 15, message = "El teléfono debe tener entre 7 y 15 caracteres")
    private String telefono;

        public MedicoRequestDto() {}

        public MedicoRequestDto(String nombre, String apellido, String matricula, Long especialidadId, String email, String telefono) {
                this.nombre = nombre;
                this.apellido = apellido;
                this.matricula = matricula;
                this.especialidadId = especialidadId;
                this.email = email;
                this.telefono = telefono;
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
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public Long getEspecialidadId() {
		return especialidadId;
	}
	public void setEspecialidadId(Long especialidadId) {
		this.especialidadId = especialidadId;
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
}
