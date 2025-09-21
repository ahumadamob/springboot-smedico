package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.Consultorio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultorioRepository;
import com.imb2025.smedico.service.IConsultorioService;
import com.imb2025.smedico.dto.ConsultorioRequestDto;


@Service
public class ConsultorioServiceImpl implements IConsultorioService {

	@Autowired
	private ConsultorioRepository repository;
	
	//Crear y guardar nuevo repositorio
	@Override
	public Consultorio create(Consultorio consultorio) {
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
	
	// Eliminar por ID
    @Override
    public void deleteById(Long id) {
    	if(!repository.existsById(id)) {
    		throw new ResourceNotFoundException("El consultorio que desea eliminar no existe");
    	}
        repository.deleteById(id);
    }
    
    //Actualizar 
    @Override
    public Consultorio update(Long id, Consultorio consultorio) throws Exception {
    	if(repository.existsById(id)) {
    		consultorio.setId(id);
    		return repository.save(consultorio);
    		
    	}else {
    		throw new RuntimeException("El Consultorio " + id + " no existe");
    	}
	}
    
    @Override
	public Consultorio fromDto(ConsultorioRequestDto dto) throws Exception {
    	
    	return new Consultorio(null, dto.getNombre(), dto.getUbicacion(), dto.getPiso());
	}

	

	
	
}
