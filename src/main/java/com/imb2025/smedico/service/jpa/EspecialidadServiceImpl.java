 package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.service.IEspecialidadService;

import com.imb2025.smedico.dto.EspecialidadRequestDto;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{

	@Autowired 
	private EspecialidadRepository repo;
	
	@Override
	public List<Especialidad> findAll() {
		return repo.findAll();		
	}

	@Override
    public Especialidad findById(Long id) {
		   return repo.findById(id)	        	    
				   .orElseThrow(() -> new ResourceNotFoundException(
	        	        "Entidad no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
    	return repo.existsById(id);
    }
		
	@Override
	public Especialidad create(Especialidad especialidad) {		
		return repo.save(especialidad);
	}
	
	@Override
	public Especialidad update(Long id, Especialidad especialidad) {
	    if (!repo.existsById(id)) {
	        throw new ResourceNotFoundException("No existe la especialidad con id " + id);
	    }
	    especialidad.setId(id);
	    return repo.save(especialidad);
	}
	public Especialidad fromDto(EspecialidadRequestDto dto) {
	
		    Especialidad especialidad = new Especialidad();
		    especialidad.setNombre(dto.getNombre());
		    especialidad.setDescripcion(dto.getDescripcion());
		    return especialidad;
	}

	@Override
	public void deleteById(Long id) {
		if(!repo.existsById(id)) {
			throw new ResourceNotFoundException("La especialidad con id " + id + " no existe");
		}
		repo.deleteById(id);

	}

	@Override
	public List<Especialidad> findByNombre(String nombre) {
		return repo.findByNombre(nombre);
	}

	@Override
	public long countByDescripcion(String descripcion) {
		return repo.countByDescripcion(descripcion);
	}
	
	
}
	



