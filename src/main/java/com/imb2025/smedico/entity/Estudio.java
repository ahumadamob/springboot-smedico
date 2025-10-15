package com.imb2025.smedico.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "estudio")
public class Estudio extends BaseEntity {

    @Column(length = 150, nullable = false)
    private String nombre;

    @Column(length = 500, nullable = false)
    private String descripcion;

    @Column(name = "especialidad_id", nullable = false)
    private Long especialidadId;

    @OneToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "resultado_estudio_id", unique = true)
    private ResultadoEstudio resultadoEstudio;

    public Estudio() {}

    public Estudio(String nombre, String descripcion,
                   Long especialidadId, ResultadoEstudio resultadoEstudio) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.especialidadId = especialidadId;
        this.resultadoEstudio = resultadoEstudio;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public Long getEspecialidad() { return especialidadId; }
    public void setEspecialidad(Long especialidadId) { this.especialidadId = especialidadId; }

    public ResultadoEstudio getResultadoEstudio() { return resultadoEstudio; }
    public void setResultadoEstudio(ResultadoEstudio resultadoEstudio) { this.resultadoEstudio = resultadoEstudio; }
}
