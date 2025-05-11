package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.HistorialPaciente;
import com.imb2025.smedico.repository.HistorialPacienteRepository;
import com.imb2025.smedico.service.IHistorialPacienteService;


@Service
public class HistorialPacienteServiceImpl implements IHistorialPacienteService {
	
	@Autowired
	private HistorialPacienteRepository repo;

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
		repo.deleteById(id);		
	}
	@Override
	public HistorialPaciente update(Long id, HistorialPaciente historial) {
	    HistorialPaciente existente = findById(id);
	    if (existente != null) {
	        existente.setPacienteId(historial.getPacienteId());
	        existente.setEvento(historial.getEvento());
	        existente.setFecha(historial.getFecha());
	        existente.setObservacion(historial.getObservacion());
	        return repo.save(existente);
	    } else {
	        return null;
	    }
	}

}
