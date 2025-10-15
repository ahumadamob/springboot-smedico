package com.imb2025.smedico.service.jpa;

import java.time.LocalDate;
import java.util.List;    

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.request.OrdenEstudioRequestDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
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
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEstudio no encontrada con id " + id));
    }



    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("No existe OrdenEstudio con id " + id);
        }
        repo.deleteById(id);
    }


    @Override
    public OrdenEstudio create(OrdenEstudio ordenestudio) {
        return repo.save(ordenestudio);
    }
    


    @Override
    public OrdenEstudio update(Long id, OrdenEstudio ordenestudio) {
        if (repo.existsById(id)) {
            ordenestudio.setId(id);
            return repo.save(ordenestudio);
        } else {
            throw new ResourceNotFoundException("No existe la orden de estudio con id " + id);
        }
    }
    

    
 
	@Override
	public boolean existsById(Long id) {
	    return repo.existsById(id);
	}

	@Override
	public List<OrdenEstudio> findByFecha(LocalDate fecha) {
	    return repo.findByFecha(fecha);
	}

	@Override
	public long countByMedico(Medico medico) {
		return repo.countByMedico(medico);
	}


	
	}