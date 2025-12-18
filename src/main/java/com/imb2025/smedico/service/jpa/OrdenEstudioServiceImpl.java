package com.imb2025.smedico.service.jpa;

import java.time.LocalDate;
import java.util.List;    

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.request.OrdenEstudioRequestDto;
import com.imb2025.smedico.dto.response.OrdenEstudioResponseDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.mapper.OrdenEstudioMapper;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IOrdenEstudioService;

import utilities.EstadoOrden;

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
	
	@Autowired 
	private OrdenEstudioMapper mapper;
	
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
    public OrdenEstudioResponseDto findByPrioridad(Long id,Integer prioridad) throws Exception {
    	
    	   OrdenEstudio existente = repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException("OrdenEstudio no encontrada con id " + id));

    	
    	if (prioridad == null) {
			 new Exception("La prioridad no puede ser nula");
		} 
    	
    	return mapper.toDto(existente);
    }

	/*
	@Override
	public 	List<OrdenEstudio> findByPrioridadGreaterThanEqual(int prioridad){
		return repo.findByPrioridadGreaterThanEqual(prioridad);
	};
	
	@Override
	public List<OrdenEstudio> findByPrioridadLessThanEqual(int prioridad) {
		return repo.findByPrioridadLessThanEqual(prioridad);
	};
	*/
/*
	@Override
	public List<OrdenEstudio> mostrarPrioridadMayorQue(int prioridad) {

		return repo.findByPrioridadGreaterThanEqual(prioridad);
	}

	@Override
	public List<OrdenEstudio> mostrarPrioridadMenorQue(int prioridad) {

		return repo.findByPrioridadLessThanEqual(prioridad);
	}
	*/
	
    @Override 
    public List<OrdenEstudio> mostrarPrioridadMayorQue(int prioridad){
    	return repo.findByPrioridadGreaterThanEqual(prioridad);
    };

    public List<OrdenEstudio> mostrarPrioridadMenorQue(int prioridad){
    	return repo.findByPrioridadLessThanEqual(prioridad);
    }

	
    @Override
    public OrdenEstudioResponseDto update(Long id, OrdenEstudioRequestDto dto) {
        // Traer la entidad existente
        OrdenEstudio existente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("OrdenEstudio no encontrada con id " + id));

        // Traer las entidades relacionadas
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Medico no encontrado con id " + dto.getMedicoId()));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado con id " + dto.getPacienteId()));

        Estudio estudio = estudioRepository.findById(dto.getEstudioId())
                .orElseThrow(() -> new ResourceNotFoundException("Estudio no encontrado con id " + dto.getEstudioId()));

        // Actualizar solo los campos que vienen del DTO
        existente.setFecha(dto.getFecha());
        existente.setMedico(medico);
        existente.setPaciente(paciente);
        existente.setEstudio(estudio);
        existente.setAutorizado(dto.getAutorizado());
        existente.setCodigoOrden(dto.getCodigoOrden());
        existente.setEstadoOrden(dto.getEstadoOrden());
        existente.setPrioridad(dto.getPrioridad());

        // Guardar la entidad actualizada
        OrdenEstudio actualizado = repo.save(existente);

        // Mapear a DTO de respuesta
        return mapper.toDto(actualizado);
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

	
	@Override
	public 	List<OrdenEstudio> findByAutorizadoTrue() {
		return repo.findByAutorizadoTrue();
	}
	@Override
	public List<OrdenEstudio> findByAutorizadoFalse() {
		return repo.findByAutorizadoFalse();
	}


	@Override
	public boolean existsByCodigoOrdenIgnoreCase(String codigoOrden) {
		return repo.existsByCodigoOrdenIgnoreCase(codigoOrden);
	}
	
	@Override
	public List<OrdenEstudio>findByFechaVigenciaGreaterThanEqual(LocalDate fechaVigencia){
		return repo.findByFechaVigenciaGreaterThanEqual(fechaVigencia);
	}
	@Override
	public List<OrdenEstudio> findByFechaVigenciaLessThan(LocalDate fechaVigencia) {
		return repo.findByFechaVigenciaLessThan(fechaVigencia);
	}
	
	@Override
	public long countByEstado(EstadoOrden estadoOrden) {
		return repo.countByEstadoOrden(estadoOrden);
	}

	
	}