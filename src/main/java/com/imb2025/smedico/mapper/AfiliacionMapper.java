package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.AfiliacionRequestDto;
import com.imb2025.smedico.dto.response.AfiliacionResponseDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.ObraSocial;


public final class AfiliacionMapper {

    private AfiliacionMapper() {
    }

  
    public static Afiliacion fromDto(AfiliacionRequestDto dto, Paciente paciente, ObraSocial obra) {
        if (dto == null) {
            return null;
        }

        Afiliacion entity = new Afiliacion();
        
        entity.setNumeroAfiliado(dto.getNumeroAfiliado());
        entity.setFechaVigenciaDesde(dto.getFechaVigenciaDesde());
        entity.setFechaHasta(dto.getFechaHasta());
        entity.setPaciente(paciente);
        entity.setObra(obra);

        return entity;
    }

   
    public static AfiliacionResponseDto toResponseDto(Afiliacion entity) {
        if (entity == null) {
            return null;
        }

        AfiliacionResponseDto dto = new AfiliacionResponseDto();
        
        dto.setId(entity.getId());
        dto.setVersion(entity.getVersion());
        dto.setNumeroAfiliado(entity.getNumeroAfiliado());
        dto.setFechaVigenciaDesde(entity.getFechaVigenciaDesde());
        dto.setFechaHasta(entity.getFechaHasta());
        
        if (entity.getPaciente() != null) {
            dto.setIdPaciente(entity.getPaciente().getId());
        }
        
        if (entity.getObra() != null) {
            dto.setIdObra(entity.getObra().getId());
        }

        return dto;
    }
}