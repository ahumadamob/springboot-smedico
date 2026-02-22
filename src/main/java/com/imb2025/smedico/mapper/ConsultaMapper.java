package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.ConsultaRequestDto;
import com.imb2025.smedico.dto.response.ConsultaResponseDto;
import com.imb2025.smedico.entity.Consulta;
public final class ConsultaMapper {
    private ConsultaMapper() {}

   
    public static Consulta fromRequestDto(ConsultaRequestDto dto) {
        Consulta c = new Consulta();
        c.setFecha(dto.getFecha());
        c.setDuracionMin(dto.getDuracionMin());
        c.setComentarios(dto.getComentarios());
        return c;
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


