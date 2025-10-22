package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.dto.response.MedicamentoResponseDto;
import com.imb2025.smedico.entity.Medicamento;
import org.springframework.stereotype.Component;

@Component // Para poder inyectarlo
public class MedicamentoMapper {

    /**
     * Convierte DTO Request a Entidad (para crear)
     */
    public Medicamento toEntity(MedicamentoRequestDto dto) {
        Medicamento medicamento = new Medicamento();
        medicamento.setNombre(dto.getNombre());
        medicamento.setDosisSugerida(dto.getDosisSugerida());
        medicamento.setPresentacion(dto.getPresentacion());
        medicamento.setEsActivo(dto.getEsActivo()); // Nuevo atributo
        return medicamento;
    }

    /**
     * Convierte Entidad a DTO Response (para mostrar)
     */
    public MedicamentoResponseDto toResponseDto(Medicamento entity) {
        MedicamentoResponseDto dto = new MedicamentoResponseDto();
        dto.setId(entity.getId());
        dto.setNombre(entity.getNombre());
        dto.setDosisSugerida(entity.getDosisSugerida());
        dto.setPresentacion(entity.getPresentacion());
        dto.setEsActivo(entity.getEsActivo()); // Nuevo atributo
        return dto;
    }

    /**
     * Actualiza una Entidad existente desde un DTO (para actualizar)
     */
    public void updateEntityFromDto(MedicamentoRequestDto dto, Medicamento entity) {
        entity.setNombre(dto.getNombre());
        entity.setDosisSugerida(dto.getDosisSugerida());
        entity.setPresentacion(dto.getPresentacion());
        entity.setEsActivo(dto.getEsActivo()); // Nuevo atributo
    }
}