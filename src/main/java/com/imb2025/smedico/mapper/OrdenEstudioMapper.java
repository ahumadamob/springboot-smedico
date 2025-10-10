package com.imb2025.smedico.mapper;

import org.springframework.beans.factory.annotation.Autowired;

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
        
        
        return ordenEstudio;
    }

    public OrdenEstudioResponseDto toDto(OrdenEstudio orden) {

        OrdenEstudioResponseDto dto = new OrdenEstudioResponseDto();
        
        dto.setEstudioId(orden.getEstudio().getId()); 
        dto.setFecha(orden.getFecha());
        dto.setMedicoId(orden.getMedico().getId());   
        dto.setPacienteId(orden.getPaciente().getId());
        dto.setVersion(orden.getVersion());

        return dto;
    }
    
}

