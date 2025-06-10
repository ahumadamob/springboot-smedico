package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.RecetaRequestDTO;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.RecetaRepository;
import com.imb2025.smedico.service.IRecetaService;
@Service
public class RecetaImpl implements IRecetaService{
	
	@Autowired
	private RecetaRepository repo;
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<Receta> findAll() {
		return repo.findAll();
	}

	@Override
	public Receta findById(Long id) {
		Optional<Receta> opt;
        opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
	}

	@Override
	public Receta create(Receta receta) {
	    try {
	    
	        return repo.save(receta);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al crear la receta: " + e.getMessage());
	    }
	}
	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Receta update(Long id, Receta receta) throws Exception {
	    if (repo.existsById(id)) {
	        receta.setId(id);
	        try {
	            return repo.save(receta);
	        } catch (Exception e) {
	            throw new RuntimeException("Error al actualizar la receta: " + e.getMessage());
	        }
	    } else {
	        throw new Exception("Receta con ID " + id + " no encontrada");
	    }
	}

	

	public Receta fromDto(RecetaRequestDTO dto) throws Exception {
	    if (dto.getMedicoId() == null) {
	        throw new IllegalArgumentException("El ID del médico no puede ser nulo");
	    }
	    if (dto.getPacienteId() == null) {
	        throw new IllegalArgumentException("El ID del paciente no puede ser nulo");
	    }

	    Medico medico = medicoRepository.findById(dto.getMedicoId())
	        .orElseThrow(() -> new Exception("Médico no encontrado con ID: " + dto.getMedicoId()));

	    Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
	        .orElseThrow(() -> new Exception("Paciente no encontrado con ID: " + dto.getPacienteId()));

	    return new Receta(dto.getFecha(), dto.getObservaciones(), medico, paciente);
	}

	
	

}
