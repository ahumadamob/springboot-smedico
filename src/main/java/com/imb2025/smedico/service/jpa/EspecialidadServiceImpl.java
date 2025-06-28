package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.service.IEspecialidadService;

import dto.EspecialidadRequestDTO;

@Service
public class EspecialidadServiceImpl implements IEspecialidadService{

	@Autowired 
	private EspecialidadRepository repo;
	
	@Override
	public List<Especialidad> findall() {
		return repo.findAll();		
	}

	@Override
	public Especialidad findById(Long id) {
		Optional<Especialidad> opt;
		opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();			
		}else {
			return null;
		}
		
		
	}
		
	@Override
	public Especialidad create(Especialidad especialidad) {		
		return repo.save(especialidad);
	}
	
		@Override
	public Especialidad update(Long id, Especialidad especialidad) throws Exception {
		Optional<Especialidad> opt = repo.findById(id);
			
		 if (repo.existsById(id)) {
		        especialidad.setId(id);
			return repo.save(especialidad);
			
		    }else {
			throw new Exception("No existe esa Especialidad");
		    }
		}
		

		
		public Especialidad fromDto(EspecialidadRequestDTO dto) {
		
			    if (dto.getNombre() == null || dto.getNombre().isBlank()) {
			        throw new IllegalArgumentException("El nombre no puede estar vacío");
			    }
			    if (dto.getDescripcion() == null || dto.getDescripcion().isBlank()) {
			        throw new IllegalArgumentException("La descripción no puede vacía");
			    }
			    Especialidad especialidad = new Especialidad();
			    especialidad.setNombre(dto.getNombre());
			    especialidad.setDescripcion(dto.getDescripcion());
			    return especialidad;
		}

		@Override
		public void deleteById(Long id) {
			
			repo.deleteById(id);

		}

	
	}
	



