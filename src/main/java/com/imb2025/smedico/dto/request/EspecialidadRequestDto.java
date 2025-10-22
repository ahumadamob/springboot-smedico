package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EspecialidadRequestDto {
	
	@NotBlank(message = "El nombre no puede estar vacio")
	@Size(min = 2, max = 30, message = "El nombre debe tener mas de 2 y menos que 30 caracteres")
    private String nombre;
	@NotBlank(message = "La descripción no puede estar vacia")
	@Size(min = 2, max = 500, message = "La descripcion debe tener mas de 2 y menos de 500 caracteres")
    private String descripcion;
	private Boolean atributoBooleano;

	public EspecialidadRequestDto() {}

    public EspecialidadRequestDto(String nombre, String descripcion) {
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
    public Boolean getAtributoBooleano() {
		return atributoBooleano;
	}

	public void setAtributoBooleano(Boolean atributoBooleano) {
		this.atributoBooleano = atributoBooleano;
	}
}
