package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.Consultorio;
import com.imb2025.smedico.repository.ConsultorioRepository;
import com.imb2025.smedico.service.IConsultorioService;

import dto.ConsultorioRequestDTO;

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
		 Optional<Consultorio> opt;
			opt = repository.findById(id);
			if(opt.isPresent()) {
				return opt.get();
			}else {
				return null;
		 }
	 }
	 
	//Listar todos
	@Override
    public List<Consultorio> findAll() {
	    return repository.findAll();
	}
	
	// Eliminar por ID
    @Override
    public void deleteById(Long id) {
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
	public Consultorio fromDto(ConsultorioRequestDTO dto) throws Exception {
	    
	    return new Consultorio(dto.getNombre(), dto.getUbicacion(), dto.getPiso());
	}

	

	
	
}
