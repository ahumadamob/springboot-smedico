package com.imb2025.smedico.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ConsultorioRequestDto {

    @NotBlank(message = "El campo nombre es obligatorio")
    @Size(min = 3, max = 30,message = "El campo combre del consultorio debe tener entre 3 y 30 caracteres")
	private String nombre;
    @NotBlank(message = "El campo ubicación es obligatorio")
    @Size(min = 3, max = 30,message = "El campo ubicación del consultorio debe tener entre 3 y 30 caracteres")
    private String ubicacion;
    @NotNull(message = "El campo piso es obligatorio")
    @Min(value = 0, message = "El número de piso debe ser mayor o igual a 0")
    private int piso;
    @NotBlank(message = "El identificadorLegible es obligatorio")
    private String identificadorLegible;

    public ConsultorioRequestDto() {}

    public ConsultorioRequestDto(String nombre, String ubicacion, int piso, String identificadorLegible) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.piso = piso;
        this.identificadorLegible = identificadorLegible;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public int getPiso() {
        return piso;
    }

    public void setPiso(int piso) {
        this.piso = piso;
    }

	public String getIdentificadorLegible() {
		return identificadorLegible;
	}
	
	public void setIdentificadorLegible(String identificadorLegible) {
	        this.identificadorLegible = identificadorLegible;
	}
}
