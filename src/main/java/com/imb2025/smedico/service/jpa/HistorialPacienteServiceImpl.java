package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.HistorialPacienteRequestDTO;
import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.HistorialPacienteRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IHistorialPacienteService;


@Service
public class HistorialPacienteServiceImpl implements IHistorialPacienteService {
	
	@Autowired
	private HistorialPacienteRepository repo;
	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<HistorialPaciente> findAll() {
		return repo.findAll();
	}

	public HistorialPaciente findById(Long id) {
		Optional<HistorialPaciente> opt;
		opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}

	@Override
	public HistorialPaciente save(HistorialPaciente historial) {
		return repo.save(historial);
	}

	@Override
	public void deleteById(Long id) {
		if (!repo.existsById(id)) {
			throw new RuntimeException("No existe el historial con ID " + id);
		}
		repo.deleteById(id);
	}
	
	@Override
	public HistorialPaciente create(HistorialPaciente historial) {
	    System.out.println("Guardando historial: " + historial);
	    return repo.save(historial);
	}

	@Override
	public HistorialPaciente update(Long id, HistorialPaciente historial) throws Exception {
	    if (repo.existsById(id)) {
	        historial.setId(id);
	        return repo.save(historial);
	    } else {
	        throw new Exception("No existe el historial del paciente");
	    }
	}
	@Override
	public HistorialPaciente fromDto(HistorialPacienteRequestDTO dto) throws Exception {
		if (dto.getPacienteId() == null) {
		    throw new IllegalArgumentException("El ID del paciente no puede ser nulo");
		}
		Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
	        .orElseThrow(() -> new Exception("Paciente no encontrado" + dto.getPacienteId()));

	   return new HistorialPaciente(dto.getEvento(),dto.getFecha(),dto.getObservacion(),paciente);
	}
	
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}


}
