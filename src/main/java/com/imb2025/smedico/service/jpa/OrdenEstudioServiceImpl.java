package com.imb2025.smedico.service.jpa;

import java.util.List;   

import java.util.Optional;
import jakarta.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.imb2025.smedico.dto.OrdenEstudioRequestDTO;
import com.imb2025.smedico.entity.EstudioEntity;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IOrdenEstudioService;


@Service
public class OrdenEstudioServiceImpl implements IOrdenEstudioService{

	@Autowired
	private OrdenEstudioRepository repo;

	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;

	@Override
	public List<OrdenEstudio> findAll() {
		return repo.findAll();
	}

	@Override	
	public OrdenEstudio findById(Long id) {
		Optional<OrdenEstudio> opt = repo.findById(id);
        return opt.orElse(null);
	}
	
	
	
	@Override
	public OrdenEstudio create(OrdenEstudio ordenestudio) {
	    return repo.save(ordenestudio);
	}

	@Override
	public OrdenEstudio update(Long id, OrdenEstudio ordenestudio) throws Exception {
	    if (!repo.existsById(id)) {
	        throw new EntityNotFoundException("No se puede actualizar. No existe una orden con ID: " + id);
	    }
	    ordenestudio.setId(id); // Asegura que se actualice la entidad correcta
	    return repo.save(ordenestudio);
	}
   

	@Override
	public void deleteById(Long id) {
	if(!repo.existsById(id)) {
		throw new IllegalArgumentException("No se puede eliminar. No existe una orden con ID:"+id);
	}
		repo.deleteById(id);
		
	}


	@Override
	public OrdenEstudio fromDto(OrdenEstudioRequestDTO dto) throws Exception {
	    Medico medico = medicoRepository.findById(dto.getMedicoId())
	        .orElseThrow(() -> new Exception("Médico no encontrado con ID " + dto.getMedicoId()));

	    Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
	        .orElseThrow(() -> new Exception("Paciente no encontrado con ID " + dto.getPacienteId()));

	    EstudioEntity estudio = estudioRepository.findById(dto.getEstudioId())
	        .orElseThrow(() -> new Exception("Estudio no encontrado con ID " + dto.getEstudioId()));

	    return new OrdenEstudio(dto.getFecha(), medico, paciente, estudio);
	}
	
	
	@Override
	public boolean existsById (Long id) {
		return repo.existsById(id);
	}
	
}