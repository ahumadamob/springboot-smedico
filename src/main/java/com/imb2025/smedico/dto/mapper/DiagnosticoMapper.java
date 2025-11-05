package com.imb2025.smedico.dto.mapper;

import com.imb2025.smedico.dto.request.DiagnosticoRequestDto;
import com.imb2025.smedico.dto.response.DiagnosticoResponseDto;
import com.imb2025.smedico.entity.Diagnostico;

public class DiagnosticoMapper {

    public static Diagnostico fromDto(DiagnosticoRequestDto dto) {
        Diagnostico d = new Diagnostico();
        d.setConsultaId(dto.getConsultaId());
        d.setDescripcion(dto.getDescripcion());
        d.setFechaDiagnostico(dto.getFechaDiagnostico());
        d.setIdentificadorLegible(dto.getIdentificadorLegible());
        d.setFechaVigencia(dto.getFechaVigencia());
        return d;
    }

    public static DiagnosticoResponseDto toResponseDto(Diagnostico e) {
        DiagnosticoResponseDto r = new DiagnosticoResponseDto();
        r.setId(e.getId());
        r.setConsultaId(e.getConsultaId());
        r.setDescripcion(e.getDescripcion());
        r.setFechaDiagnostico(e.getFechaDiagnostico());
        r.setIdentificadorLegible(e.getIdentificadorLegible());
        r.setFechaVigencia(e.getFechaVigencia());
        r.setVersion(e.getVersion());
        return r;
    }
}
