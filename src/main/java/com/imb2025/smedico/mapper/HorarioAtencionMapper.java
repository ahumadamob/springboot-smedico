package com.imb2025.smedico.mapper;

import org.springframework.stereotype.Component;
import com.imb2025.smedico.dto.request.HorarioAtencionRequestDto;
import com.imb2025.smedico.dto.response.HorarioAtencionResponseDto;
import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.service.IMedicoService;

@Component
public class HorarioAtencionMapper {

    private final IMedicoService medicoService;

    public HorarioAtencionMapper(IMedicoService medicoService) {
        this.medicoService = medicoService;
    }

    public HorarioAtencion fromDto(HorarioAtencionRequestDto requestDTO) throws Exception {
        HorarioAtencion horarioEntity = new HorarioAtencion();

        if (requestDTO.getMedicoId() != null) {
            Medico medico = medicoService.findById(requestDTO.getMedicoId());
            if (medico == null) {
                throw new ResourceNotFoundException("Médico no encontrado con ID: " + requestDTO.getMedicoId());
            }
            horarioEntity.setMedico(medico);
        } else {
            horarioEntity.setMedico(null);
        }

        horarioEntity.setDiaSemana(requestDTO.getDiaSemana());
        horarioEntity.setHoraInicio(requestDTO.getHoraInicio());
        horarioEntity.setHoraFin(requestDTO.getHoraFin());
        return horarioEntity;
    }

    public HorarioAtencionResponseDto toResponseDto(HorarioAtencion horarioEntity) {
        HorarioAtencionResponseDto responseDTO = new HorarioAtencionResponseDto();
        responseDTO.setId(horarioEntity.getId());
        responseDTO.setDiaSemana(horarioEntity.getDiaSemana());
        responseDTO.setHoraInicio(horarioEntity.getHoraInicio());
        responseDTO.setHoraFin(horarioEntity.getHoraFin());
        return responseDTO;
    }
}