package com.imb2025.smedico.dto;

public class MedicoRequestDto {

    private String nombre;
    private String apellido;
    private String matricula;    
    private Long especialidadId;
    private String email;
    private String telefono;

        public MedicoRequestDto() {}

        public MedicoRequestDto(
                String nombre,
                String apellido,
                String matricula,
                Long especialidadId,
                String email,
                String telefono) {
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
