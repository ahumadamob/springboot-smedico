package com.imb2025.smedico.dto.mapper;

import com.imb2025.smedico.dto.request.DiagnosticoRequestDto;
import com.imb2025.smedico.dto.response.DiagnosticoResponseDto;
import com.imb2025.smedico.entity.Diagnostico;

public class DiagnosticoMapper {

    // Convierte de RequestDto → Entidad
    public static Diagnostico fromDto(DiagnosticoRequestDto dto) {
        Diagnostico d = new Diagnostico();
        d.setConsultaId(dto.getConsultaId());
        d.setDescripcion(dto.getDescripcion());
        d.setFechaDiagnostico(dto.getFechaDiagnostico());
        return d;
    }

    // Convierte de Entidad → ResponseDto
    public static DiagnosticoResponseDto toResponseDto(Diagnostico d) {
        return new DiagnosticoResponseDto(
                d.getId(),
                d.getConsultaId(),
                d.getDescripcion(),
                d.getFechaDiagnostico(),
                d.getVersion()
        );
    }
}
