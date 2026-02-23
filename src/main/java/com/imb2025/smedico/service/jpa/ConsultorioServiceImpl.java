package com.imb2025.smedico.service.jpa;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.Consultorio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultorioRepository;
import com.imb2025.smedico.service.IConsultorioService;

@Service
public class ConsultorioServiceImpl implements IConsultorioService {

	@Autowired
	private ConsultorioRepository repository;
	
	//Crear y guardar nuevo repositorio
	@Override
	public Consultorio create(Consultorio consultorio) {
		if (repository.existsByIdentificadorLegible(consultorio.getIdentificadorLegible())) {
			throw new IllegalArgumentException("El identificadorLegible: "+ consultorio.getIdentificadorLegible() +"ya está en uso");
		}
		return repository.save(consultorio);
	}
	
	//Buscar por id
	 @Override
	 public Consultorio findById(Long id) {
		 return repository.findById(id)
				    .orElseThrow(() -> new ResourceNotFoundException(
				        "Consultorio no encontrado con id " + id));			
	 }

    @Override
    public boolean existsById(Long id) {
         return repository.existsById(id);
    }
	 
	//Listar todos
	@Override
    public List<Consultorio> findAll() {
	    return repository.findAll();
	}
	
	//Buscar por nombre
	@Override
	public Consultorio findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}
	
	//Buscar por ubicación
	@Override
	public List<Consultorio> findByUbicacion(String ubicacion){
		return repository.findByUbicacion(ubicacion);
	}
	
	// Eliminar por ID
    @Override
    public void deleteById(Long id) {
    	repository.deleteById(id);
    }
    
    //Actualizar 
    @Override
    public Consultorio update(Long id, Consultorio consultorio){
    	Consultorio consultorioExistente = repository.findById(id)
    			.orElseThrow(() -> new ResourceNotFoundException("Consultorio no existe"));
    	consultorioExistente.setNombre(consultorio.getNombre());
    	consultorioExistente.setUbicacion(consultorio.getUbicacion());
    	consultorioExistente.setPiso(consultorio.getPiso());
    	consultorioExistente.setIdentificadorLegible(consultorio.getIdentificadorLegible());
    	
    	return repository.save(consultorioExistente);
	}
    
    /*@Override
	public Consultorio fromDto(ConsultorioRequestDto dto) {
    	if(dto.getNombre() == null || dto.getNombre().isBlank()) {
    		throw new IllegalArgumentException("El nombre no puede estar vacío");
    	}
    	if(dto.getUbicacion() == null || dto.getUbicacion().isBlank()) {
    		throw new IllegalArgumentException("La ubicación no puede estar vacía");
    	}
    	if(dto.getPiso() == 0) {
    		throw new IllegalArgumentException("El piso no puede ser nulo");
    	}
    	if(dto.getIdentificadorLegible() == null || dto.getIdentificadorLegible().isBlank()) {
    		throw new IllegalArgumentException("El identificador visible no puede estar vacío");
    	}
    	Consultorio consultorio = new Consultorio();
    	
    	consultorio.setNombre(dto.getNombre());
    	consultorio.setUbicacion(dto.getUbicacion());
    	consultorio.setPiso(dto.getPiso());
    	consultorio.setIdentificadorLegible(dto.getIdentificadorLegible());
    	
    	return consultorio;
	}	*/
}
