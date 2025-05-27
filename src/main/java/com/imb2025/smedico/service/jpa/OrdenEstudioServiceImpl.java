
package com.imb2025.smedico.service.jpa;

import java.util.List;   
import java.util.Optional;

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
          Optional<OrdenEstudio> ord = repo.findById(id);
          return ord.orElse(null);
	}



	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
		
	}

	@Override
	public OrdenEstudio create(OrdenEstudio ordenestudio)  {
		
	    System.out.println("Guardando orden: " + ordenestudio);

    	 return repo.save(ordenestudio);
	}
	

	@Override
	public OrdenEstudio update(Long id,OrdenEstudio ordenestudio) throws Exception {
		if(repo.existsById(id)) {
			ordenestudio.setId(id);
		      return repo.save(ordenestudio);
		}else {
			throw new Exception("No existe la orden de estudio");
		}
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

		
		
	}

	
	
	

