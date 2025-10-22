package com.imb2025.smedico.dto.response;



import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.entity.Medico.Estado;



public class MedicoResponseDto {
	 
  private Long id;
  private Long version;
  private String nombre;
  private String apellido;
  private String matricula;
  private Especialidad especialidad;
  private String email;
  private Estado estado;
  
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
public String getMatricula() {
	return matricula;
}
public void setMatricula(String matricula) {
	this.matricula = matricula;
}
public Especialidad getEspecialidad() {
	return especialidad;
}
public void setEspecialidad(Especialidad especialidad) {
	this.especialidad = especialidad;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}

public Estado getEstado() { 
	return estado; 
}
public void setEstado(Estado estado) { 
	this.estado = estado; 
}
  
  
 
}
