package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MotivoCancelacionRequestDto {

	 	@NotBlank(message = "El nombre no puede ser vacío")
	    @Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
	    private String nombre;
	    @NotBlank(message = "La descripción no puede estar vacía")
	    @Size(min = 3, max = 200, message = "La descripción debe tener entre 3 y 200 caracteres")
	    private String descripcion;
	    @NotNull(message ="El alias no puede ser nulo")
	    @Size(min = 4, max = 30, message = "El alias debe tener entre 4 y 30 caracteres")
	    private String alias;
		
        public MotivoCancelacionRequestDto() {}

        public MotivoCancelacionRequestDto(String nombre, String descripcion,String alias) {
                this.nombre = nombre;
                this.descripcion = descripcion;
                this.alias=alias;
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

	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	
}
