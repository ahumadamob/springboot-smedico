package com.imb2025.smedico.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class EstudioRequestDto {
	
	@NotNull(message = "El ID del paciente es obligatorio.")
    @Positive(message = "El ID del paciente debe ser un número positivo.")
    private Long pacientId;

	@NotBlank(message = "El nombre del estudio no puede estar vacío.")
    @Size(min = 3, max = 150, message = "El nombre debe tener entre 3 y 150 caracteres.")
    private String nombre;

    @NotBlank(message = "La descripción no puede estar vacía.")
    @Size(min = 10, max = 500, message = "La descripción debe tener entre 10 y 500 caracteres.")
    private String descripcion;

    @NotNull(message = "El ID de la especialidad es obligatorio.")
    @Positive(message = "El ID de la especialidad debe ser un número positivo.")
    private Long especialidadId;

    @Positive(message = "El ID del resultado de estudio debe ser un número positivo.")
    private Long resultadoEstudioId;

    public EstudioRequestDto() {}

    public EstudioRequestDto(
    		Long pacientId,
            String nombre,
            String descripcion,
            Long especialidadId,
            Long resultadoEstudioId) {
    	this.pacientId = pacientId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especialidadId = especialidadId;
        this.resultadoEstudioId = resultadoEstudioId;
    }
    public Long getPacientId() {
        return pacientId;
    }

    public void setPacientId(Long pacientId) {
        this.pacientId = pacientId;
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

    public Long getEspecialidadId() {
        return especialidadId;
    }

    public void setEspecialidadId(Long especialidadId) {
        this.especialidadId = especialidadId;
    }

    public Long getResultadoEstudioId() {
        return resultadoEstudioId;
    }

    public void setResultadoEstudioId(Long resultadoEstudioId) {
        this.resultadoEstudioId = resultadoEstudioId;
    }
}
