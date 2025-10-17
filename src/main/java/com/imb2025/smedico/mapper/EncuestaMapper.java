// ==========================
// 2) Mapper: EncuestaMapper
// ==========================
package com.imb2025.smedico.mapper;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.imb2025.smedico.dto.request.EncuestaRequestDto;
import com.imb2025.smedico.dto.response.EncuestaResponseDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.PacienteRepository;

@Component
public class EncuestaMapper {

    private final ConsultaRepository repoConsulta;
    private final PacienteRepository repoPaciente;

    public EncuestaMapper(ConsultaRepository repoConsulta, PacienteRepository repoPaciente) {
        this.repoConsulta = repoConsulta;
        this.repoPaciente = repoPaciente;
    }

    @Transactional(readOnly = true)
    public Encuesta fromDto(EncuestaRequestDto dto) {
        if (dto == null) return null;

        Paciente paciente = repoPaciente.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id " + dto.getPacienteId()));

        Consulta consulta = repoConsulta.findById(dto.getConsultaId())
            .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id " + dto.getConsultaId()));

        Encuesta e = new Encuesta();
        e.setPaciente(paciente);
        e.setConsulta(consulta);
        e.setPuntaje(dto.getPuntaje());
        e.setComentario(dto.getComentario());
        return e;
    }

    public EncuestaResponseDto toDto(Encuesta e) {
        if (e == null) return null;

        EncuestaResponseDto dto = new EncuestaResponseDto();
        dto.setId(e.getId());
        dto.setVersion(e.getVersion());
        dto.setComentario(e.getComentario());
        dto.setPuntaje(e.getPuntaje());
        dto.setConsultaId(e.getConsulta() != null ? e.getConsulta().getId() : null);
        dto.setPacienteId(e.getPaciente() != null ? e.getPaciente().getId() : null);
        return dto;
    }
}
