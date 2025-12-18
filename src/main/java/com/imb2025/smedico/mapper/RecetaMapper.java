package com.imb2025.smedico.mapper;

import org.springframework.beans.factory.annotation.Autowired;

import com.imb2025.smedico.dto.request.RecetaRequestDto;
import com.imb2025.smedico.dto.response.RecetaResponseDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.PacienteRepository;

public class RecetaMapper {
	@Autowired
	private MedicoRepository repoMedico;
	@Autowired
	private PacienteRepository repoPaciente;
	
	
    public Receta fromDto(RecetaRequestDto dto) {
		Medico medico = repoMedico.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Médico no encontrado con ID: " + dto.getMedicoId()));
        Paciente paciente = repoPaciente.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado con ID: " + dto.getPacienteId()));

        Receta receta = new Receta();
        receta.setFecha(dto.getFecha());
        receta.setMedico(medico);
        receta.setObservaciones(dto.getObservaciones());
        receta.setPaciente(paciente);

        return receta;
    }
    
    public RecetaResponseDto toDto(Receta receta) {
    
    	RecetaResponseDto dto= new RecetaResponseDto();
    	dto.setFecha(receta.getFecha());
    	dto.setMedico(receta.getMedico());
    	dto.setObservaciones(receta.getObservaciones());
    	dto.setPaciente(receta.getPaciente());
    	dto.setVersion(receta.getVersion());
    	
    	return dto;
    	
    }

}
