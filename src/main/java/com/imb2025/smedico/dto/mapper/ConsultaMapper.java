package com.imb2025.smedico.dto.mapper;

import com.imb2025.smedico.dto.request.ConsultaRequestDto;
import com.imb2025.smedico.dto.response.ConsultaResponseDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Turno;
public final class ConsultaMapper {
    private ConsultaMapper() {}

    public static Consulta fromDto(ConsultaRequestDto dto, Turno turno) {
        Consulta c = new Consulta();
        c.setFecha(dto.getFecha());
        c.setDuracionMin(dto.getDuracionMin());
        c.setComentarios(dto.getComentarios());
        c.setTurno(turno);
        return c;
    }

    public static void copyFromDto(ConsultaRequestDto dto, Turno turno, Consulta destino) {
        destino.setFecha(dto.getFecha());
        destino.setDuracionMin(dto.getDuracionMin());
        destino.setComentarios(dto.getComentarios());
        destino.setTurno(turno);
    }

    public static ConsultaResponseDto toResponseDto(Consulta c) {
        return new ConsultaResponseDto(
            c.getId(),
            c.getFecha(),
            c.getDuracionMin(),
            c.getComentarios(),
            c.getTurno() != null ? c.getTurno().getId() : null,
            c.getVersion()
        );
    }
}

