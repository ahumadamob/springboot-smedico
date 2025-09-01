package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class MotivoCancelacionRequestDto {

		@NotBlank(message = "El nombre no puede ser vacio")
		@Size(min = 3, max = 50, message = "El nombre debe tener entre 3 y 50 caracteres")
        private String nombre;
		@Size(min = 5, max = 200, message = "La descripcion debe tener entre 3 y 200 caracteres")
		@NotBlank(message = "La descripcion no puede estar vacia")
        private String descripcion;
		
        public MotivoCancelacionRequestDto() {}

        public MotivoCancelacionRequestDto(String nombre, String descripcion) {
                this.nombre = nombre;
                this.descripcion = descripcion;
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
	
	
}
