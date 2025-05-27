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
	public Especialidad create(EspecialidadRequestDTO dto) {
		Especialidad especialidad = fromDto(dto);
		return repo.save(especialidad);
	}
	
		@Override
	public Especialidad update(Long id, EspecialidadRequestDTO dto) throws Exception {
		Optional<Especialidad> opt = repo.findById(id);
			
			if (opt.isPresent()){
			Especialidad especialidad = opt.get();
			
			especialidad.setNombre(dto.getNombre());
			
			especialidad.setDescripcion(dto.getDescripcion());
			
			return repo.save(especialidad);
			
		    }else {
			throw new Exception("No existe esa Especialidad");
		    }
		}
		

		
		private Especialidad fromDto(EspecialidadRequestDTO dto) {
		
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
	



