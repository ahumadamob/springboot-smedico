package com.imb2025.dto.mapper;

import com.imb2025.smedico.dto.request.AfiliacionRequestDto;
import com.imb2025.smedico.dto.response.AfiliacionResponseDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.ObraSocial;

public final class AfiliacionMapper {

    private AfiliacionMapper() {}

    // Construye la entidad a partir del request + asociaciones ya resueltas
    public static Afiliacion fromDto(AfiliacionRequestDto dto, Paciente paciente, ObraSocial obra) {
        if (dto == null) return null;
        Afiliacion af = new Afiliacion();
        af.setNumeroAfiliado(dto.getNumeroAfiliado());
        af.setFechaVigenciaDesde(dto.getFechaVigenciaDesde());
        af.setFechaHasta(dto.getFechaHasta());
        af.setPaciente(paciente);
        af.setObra(obra);
        return af;
    }

    // Convierte Entidad -> ResponseDto (incluye version, NO audit)
    public static AfiliacionResponseDto toResponseDto(Afiliacion af) {
        if (af == null) return null;
        Long idPac = (af.getPaciente() != null) ? af.getPaciente().getId() : null;
        Long idObra = (af.getObra() != null) ? af.getObra().getId() : null;

        AfiliacionResponseDto dto = new AfiliacionResponseDto();
        dto.setId(af.getId());
        dto.setNumeroAfiliado(af.getNumeroAfiliado());
        dto.setFechaVigenciaDesde(af.getFechaVigenciaDesde());
        dto.setFechaHasta(af.getFechaHasta());
        dto.setIdPaciente(idPac);
        dto.setIdObra(idObra);
        dto.setVersion(af.getVersion());
        return dto;
    }
}
