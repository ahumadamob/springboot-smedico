package com.imb2025.smedico.dto;



public class EstadoTurnoDTO {
	   private String nombre;

	    // Constructor vacío
	    public EstadoTurnoDTO() {}

	    // Constructor con parámetros
	    public EstadoTurnoDTO(String nombre) {
	        this.nombre = nombre;
	    }

	    // Getters y setters
	    public String getNombre() {
	        return nombre;
	    }

	    public void setNombre(String nombre) {
	        this.nombre = nombre;
	    }


}
