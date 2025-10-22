package com.imb2025.smedico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estudio")
public class Estudio extends BaseEntity {

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 500, nullable = false)
    private String descripcion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "especialidad_id", nullable = false)
    private Especialidad especialidad;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "resultado_estudio_id", unique = true)
    private ResultadoEstudio resultadoEstudio;
    
    @Column(nullable = false)
    private Boolean habilitado = true;

    public Estudio() {}

    public Estudio(String nombre, String descripcion,
                   Especialidad especialidad, ResultadoEstudio resultadoEstudio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especialidad = especialidad;
        this.resultadoEstudio = resultadoEstudio;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Especialidad getEspecialidad() { return especialidad; }
    public void setEspecialidad(Especialidad especialidad) { this.especialidad = especialidad; }

    public ResultadoEstudio getResultadoEstudio() { return resultadoEstudio; }
    public void setResultadoEstudio(ResultadoEstudio resultadoEstudio) { this.resultadoEstudio = resultadoEstudio; }
    
    public Boolean getHabilitado() { return habilitado; }
    public void setHabilitado(Boolean habilitado) { this.habilitado = habilitado; }
}
