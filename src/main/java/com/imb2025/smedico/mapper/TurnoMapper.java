package com.imb2025.smedico.mapper;

import com.imb2025.smedico.dto.request.TurnoRequestDto.TurnoRequestDto;
import com.imb2025.smedico.dto.response.TurnoResponseDto.TurnoResponseDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Turno;

public class TurnoMapper {

    // Crea un Turno desde un RequestDto y entidades relacionadas
    public static Turno fromDto(TurnoRequestDto dto,
                                Paciente paciente,
                                Medico medico,
                                EstadoTurno estadoTurno) {
        Turno turno = new Turno();
        turno.setFecha(dto.getFecha());
        turno.setHora(dto.getHora());
        turno.setPaciente(paciente);
        turno.setMedico(medico);
        turno.setEstadoTurno(estadoTurno);
        turno.setFechaVigencia(dto.getFechaVigencia()); 
        return turno;
    }

    // Convierte un Turno a ResponseDto
    public static TurnoResponseDto toResponseDto(Turno turno) {
        TurnoResponseDto dto = new TurnoResponseDto();
        dto.setId(turno.getId());
        dto.setFecha(turno.getFecha());
        dto.setHora(turno.getHora());
        dto.setPacienteNombre(turno.getPaciente().getNombre());
        dto.setMedicoNombre(turno.getMedico().getNombre());
        dto.setEstadoTurnoDescripcion(turno.getEstadoTurno().getNombre()); // o descripcion si tu entidad tiene ese campo
        dto.setFechaVigencia(turno.getFechaVigencia());
        return dto;
    }

    // Actualiza un Turno existente con los datos de un RequestDto
    public static void updateEntityFromDto(TurnoRequestDto dto,
                                           Turno turno,
                                           Paciente paciente,
                                           Medico medico,
                                           EstadoTurno estadoTurno) {
        turno.setFecha(dto.getFecha());
        turno.setHora(dto.getHora());
        turno.setPaciente(paciente);
        turno.setMedico(medico);
        turno.setEstadoTurno(estadoTurno);
        turno.setFechaVigencia(dto.getFechaVigencia());
        // version no se setea, JPA lo maneja automáticamente
    }
}
