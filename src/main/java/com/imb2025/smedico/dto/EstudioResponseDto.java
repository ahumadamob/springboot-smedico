package com.imb2025.smedico.dto;

import com.imb2025.smedico.entity.Estudio;

public class EstudioResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private Long especialidadId;
    private String resultadoDescripcion;

    public EstudioResponseDto(Estudio estudio) {
        this.id = estudio.getId();
        this.nombre = estudio.getNombre();
        this.descripcion = estudio.getDescripcion();
        this.especialidadId = estudio.getEspecialidad();
        this.resultadoDescripcion = (estudio.getResultadoEstudio() != null)
                ? estudio.getResultadoEstudio().getObservaciones()
                : null;
    }

    public EstudioResponseDto() {}

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getEspecialidadId() { return especialidadId; }
    public void setEspecialidadId(Long especialidadId) { this.especialidadId = especialidadId; }

    public String getResultadoDescripcion() { return resultadoDescripcion; }
    public void setResultadoDescripcion(String resultadoDescripcion) { this.resultadoDescripcion = resultadoDescripcion; }
}
