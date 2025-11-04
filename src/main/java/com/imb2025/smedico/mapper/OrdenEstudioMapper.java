package com.imb2025.smedico.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.imb2025.smedico.dto.request.OrdenEstudioRequestDto;
import com.imb2025.smedico.dto.response.OrdenEstudioResponseDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
@Component
public class OrdenEstudioMapper {
    

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
 
    public OrdenEstudio fromDto(OrdenEstudioRequestDto dto) {
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado con ID " + dto.getMedicoId()));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con ID " + dto.getPacienteId()));

        Estudio estudio = estudioRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado con ID " + dto.getEstudioId()));

        OrdenEstudio ordenEstudio = new OrdenEstudio();
        ordenEstudio.setEstudio(estudio);
        ordenEstudio.setFecha(dto.getFecha());
        ordenEstudio.setMedico(medico);
        ordenEstudio.setPaciente(paciente);
        ordenEstudio.setCodigoOrden(dto.getCodigoOrden());
        ordenEstudio.setFechaVigencia(dto.getFechaVigencia());
        
        return ordenEstudio;
    }

    public OrdenEstudioResponseDto toDto(OrdenEstudio orden) {

        OrdenEstudioResponseDto dto = new OrdenEstudioResponseDto();
        dto.setId(orden.getId());
        dto.setVersion(orden.getVersion());
        dto.setFecha(orden.getFecha());

        
        dto.setEstudio(orden.getEstudio());
        dto.setMedico(orden.getMedico());
        dto.setPaciente(orden.getPaciente());
        dto.setCodigoOrden(orden.getCodigoOrden());
        dto.setFechaVigencia(orden.getFechaVigencia());
        return dto;
    }

    // Método de INSTANCIA para convertir listas
    public List<OrdenEstudioResponseDto> toResponseDtoList(List<OrdenEstudio> entidades) {
        return entidades.stream()
            .map(this::toDto)
            .collect(Collectors.toList());
    }
}

