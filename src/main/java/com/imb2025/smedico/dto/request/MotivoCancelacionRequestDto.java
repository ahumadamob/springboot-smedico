package com.imb2025.smedico.dto.request;

import com.imb2025.smedico.entity.MotivoCancelacion.Estado;
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
	    @NotNull(message = "El estado es obligatorio")
	    private Estado estado;
	    
        public MotivoCancelacionRequestDto() {}

        public MotivoCancelacionRequestDto(String nombre, String descripcion,Estado estado) {
                this.nombre = nombre;
                this.descripcion = descripcion;
                this.estado = estado;
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
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	
	
}
