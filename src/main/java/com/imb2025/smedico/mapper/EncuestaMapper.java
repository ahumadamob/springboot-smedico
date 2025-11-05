package com.imb2025.smedico.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

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

    @Autowired
    private PacienteRepository pacienteRepo;

    @Autowired
    private ConsultaRepository consultaRepo;

    public Encuesta fromDto(EncuestaRequestDto dto) {
        Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id=" + dto.getPacienteId()));

        Consulta consulta = consultaRepo.findById(dto.getConsultaId())
            .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id=" + dto.getConsultaId()));

        Encuesta e = new Encuesta();
        e.setPaciente(paciente);
        e.setConsulta(consulta);
        e.setPuntaje(dto.getPuntaje());
        e.setComentario(dto.getComentario());
        return e;
    }

    public EncuestaResponseDto toDto(Encuesta encuesta) {
        EncuestaResponseDto dto = new EncuestaResponseDto();
        dto.setId(encuesta.getId());
        dto.setVersion(encuesta.getVersion());
        dto.setComentario(encuesta.getComentario());
        dto.setPuntaje(encuesta.getPuntaje());
        dto.setConsulta(encuesta.getConsulta());   
        dto.setPaciente(encuesta.getPaciente());   
        return dto;
    }
}
