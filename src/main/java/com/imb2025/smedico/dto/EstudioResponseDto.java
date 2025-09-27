package com.imb2025.smedico.dto;

public class EstudioResponseDto {

    private Long id;
    private String nombre;
    private String descripcion;
    private String especialidadNombre;
    private String resultadoDescripcion;

    public EstudioResponseDto(com.imb2025.smedico.entity.Estudio estudio) {
        this.id = estudio.getId();
        this.nombre = estudio.getNombre();
        this.descripcion = estudio.getDescripcion();
        this.especialidadNombre = (estudio.getEspecialidad() != null)
                ? estudio.getEspecialidad().getNombre()
                : null;
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

    public String getEspecialidadNombre() { return especialidadNombre; }
    public void setEspecialidadNombre(String especialidadNombre) { this.especialidadNombre = especialidadNombre; }

    public String getResultadoDescripcion() { return resultadoDescripcion; }
    public void setResultadoDescripcion(String resultadoDescripcion) { this.resultadoDescripcion = resultadoDescripcion; }
}
