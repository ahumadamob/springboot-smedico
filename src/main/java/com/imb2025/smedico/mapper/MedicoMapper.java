package com.imb2025.smedico.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imb2025.smedico.dto.request.MedicoRequestDto;
import com.imb2025.smedico.dto.response.MedicoResponseDto;
import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.repository.EspecialidadRepository;

@Component
public class MedicoMapper {

    @Autowired
    private EspecialidadRepository repoEspecialidad;

    public Medico fromDto(MedicoRequestDto dto) {
        Especialidad especialidad = repoEspecialidad.findById(dto.getEspecialidadId())
            .orElseThrow(() -> new RuntimeException("No se encontró especialidad con el id: " + dto.getEspecialidadId()));

        Medico medico = new Medico();
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setMatricula(dto.getMatricula());
        medico.setEspecialidad(especialidad);
        medico.setEmail(dto.getEmail());
        medico.setTelefono(dto.getTelefono());

        return medico;
    }

    public MedicoResponseDto toDto(Medico medico) {
        MedicoResponseDto dto = new MedicoResponseDto();
        dto.setApellido(medico.getApellido());
        dto.setEmail(medico.getEmail());
        dto.setEspecialidad(medico.getEspecialidad());
        dto.setMatricula(medico.getMatricula());
        dto.setNombre(medico.getNombre());
        dto.setVersion(medico.getVersion());
        return dto;
    }
}
