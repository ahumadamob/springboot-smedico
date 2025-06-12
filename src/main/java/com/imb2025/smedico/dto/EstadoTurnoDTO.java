package com.imb2025.smedico.dto;

import com.imb2025.smedico.entity.EstadoTurno;

public class EstadoTurnoDTO {
    private String nombre;

    public EstadoTurnoDTO() {}

    public EstadoTurnoDTO(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //  Conversor de DTO a entidad
    public static EstadoTurno fromDto(EstadoTurnoDTO dto) {
        EstadoTurno estadoTurno = new EstadoTurno();
        estadoTurno.setNombre(dto.getNombre());
        return estadoTurno;
    }
}
