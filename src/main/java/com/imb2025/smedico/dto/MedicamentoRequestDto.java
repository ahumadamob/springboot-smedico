package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class MedicamentoRequestDto {
        @NotBlank(message = "El nombre del medicamento no puede estar vacio")
        @Size(min = 2, max = 100, message = "Debe contener entre 2 y 50 caracteres")
		private String nombre;
        
        @NotBlank(message = "Se debe especificar cantidad y tipo del medicamento")
        @Size(min = 2, max = 50)
        private String presentacion;
        
        @Size(max = 100, message = "Se permiten hasta 100 caracteres")
        private String dosisSugerida;
        
        @NotNull(message = "Debe especificar si el medicamento está activo")
        private Boolean esActivo;

        public MedicamentoRequestDto() {}

        public MedicamentoRequestDto(String nombre, String presentacion, String dosisSugerida, Boolean esActivo) {
                this.nombre = nombre;
                this.presentacion = presentacion;
                this.dosisSugerida = dosisSugerida;
                this.esActivo = esActivo;

        }

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPresentacion() {
		return presentacion;
	}

	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}

	public String getDosisSugerida() {
		return dosisSugerida;
	}

	public void setDosisSugerida(String dosisSugerida) {
		this.dosisSugerida = dosisSugerida;
	}

	public Boolean getEsActivo() {
        return esActivo;
    }
    public void setEsActivo(Boolean esActivo) {
        this.esActivo = esActivo;
    }	

}
