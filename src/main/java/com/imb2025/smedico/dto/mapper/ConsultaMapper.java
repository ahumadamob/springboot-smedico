package com.imb2025.smedico.dto.mapper;

import com.imb2025.smedico.dto.request.ConsultaRequestDto;
import com.imb2025.smedico.dto.response.ConsultaResponseDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Turno;

public final class ConsultaMapper {
    
    private ConsultaMapper() {}

    // ✅ Convierte Request DTO a Entidad (para crear)
    public static Consulta fromDto(ConsultaRequestDto dto, Turno turno) {
        Consulta c = new Consulta();
        c.setFecha(dto.getFecha());
        c.setDuracionMin(dto.getDuracionMin());
        c.setComentarios(dto.getComentarios());
        c.setFechaVigencia(dto.getFechaVigencia());
        c.setIdentificadorLegible(dto.getIdentificadorLegible());  // ✅ NUEVO
        c.setTurno(turno);
        return c;
    }

    // ✅ Copia datos de DTO a Entidad existente (para update)
    public static void copyFromDto(ConsultaRequestDto dto, Turno turno, Consulta destino) {
        destino.setFecha(dto.getFecha());
        destino.setDuracionMin(dto.getDuracionMin());
        destino.setComentarios(dto.getComentarios());
        destino.setFechaVigencia(dto.getFechaVigencia());
        destino.setIdentificadorLegible(dto.getIdentificadorLegible());  // ✅ NUEVO
        destino.setTurno(turno);
    }

    // ✅ Convierte Entidad a Response DTO
    public static ConsultaResponseDto toResponseDto(Consulta c) {
        return new ConsultaResponseDto(
            c.getId(),
            c.getFecha(),
            c.getDuracionMin(),
            c.getComentarios(),
            c.getTurno() != null ? c.getTurno().getId() : null,
            c.getVersion(),
            c.getFechaVigencia(),
            c.getIdentificadorLegible()  // ✅ NUEVO
        );
    }
}