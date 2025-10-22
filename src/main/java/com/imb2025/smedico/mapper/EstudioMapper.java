package com.imb2025.smedico.mapper;

import org.springframework.stereotype.Component;

import com.imb2025.smedico.dto.EstudioResponseDto;
import com.imb2025.smedico.dto.request.EstudioRequestDto;
import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;

@Component
public class EstudioMapper {

    private final EspecialidadRepository repoEspecialidad;
    private final ResultadoEstudioRepository repoResultadoEstudio;

    public EstudioMapper(EspecialidadRepository repoEspecialidad,
                         ResultadoEstudioRepository repoResultadoEstudio) {
        this.repoEspecialidad = repoEspecialidad;
        this.repoResultadoEstudio = repoResultadoEstudio;
    }

    public Estudio fromDto(EstudioRequestDto dto) {

        Especialidad especialidad = repoEspecialidad.findById(dto.getEspecialidadId())
            .orElseThrow(() -> new ResourceNotFoundException(
                "Especialidad no encontrada: " + dto.getEspecialidadId()));

       
        ResultadoEstudio resultadoEstudio = null;
        if (dto.getResultadoEstudioId() != null) {
            resultadoEstudio = repoResultadoEstudio.findById(dto.getResultadoEstudioId())
                .orElseThrow(() -> new ResourceNotFoundException(
                    "Resultado de estudio no encontrado: " + dto.getResultadoEstudioId()));
        }

        Estudio estudio = new Estudio();
        estudio.setNombre(dto.getNombre());
        estudio.setDescripcion(dto.getDescripcion());
        estudio.setEspecialidad(especialidad);
        estudio.setResultadoEstudio(resultadoEstudio);
        
        estudio.setHabilitado(dto.getHabilitado());
        return estudio;
    }

    public EstudioResponseDto toDto(Estudio estudio) {
        EstudioResponseDto dto = new EstudioResponseDto();
        dto.setId(estudio.getId());
        dto.setNombre(estudio.getNombre());
        dto.setDescripcion(estudio.getDescripcion());
        dto.setHabilitado(estudio.getHabilitado());
        return dto;
    }
}