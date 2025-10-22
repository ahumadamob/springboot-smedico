package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.dto.request.ConsultorioRequestDto;
import com.imb2025.smedico.dto.response.ConsultorioResponseDto;
import com.imb2025.smedico.entity.Consultorio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.mapper.ConsultorioMapper;
import com.imb2025.smedico.repository.ConsultorioRepository;
import com.imb2025.smedico.service.IConsultorioService;

@Service
public class ConsultorioServiceImpl implements IConsultorioService {

	@Autowired
	private ConsultorioRepository repository;
	private ConsultorioMapper mapper;
	
	public ConsultorioServiceImpl(ConsultorioRepository repository, ConsultorioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
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
    	if(dto.getNombre() == null || dto.getNombre().isBlank()) {
    		throw new IllegalArgumentException("El nombre no puede estar vacío");
    	}
    	if(dto.getUbicacion() == null || dto.getUbicacion().isBlank()) {
    		throw new IllegalArgumentException("La ubicación no puede estar vacía");
    	}
    	if(dto.getPiso() == 0) {
    		throw new IllegalArgumentException("El piso no puede ser nulo");
    	}
    	Consultorio consultorio = new Consultorio();
    	
    	consultorio.setNombre(dto.getNombre());
    	consultorio.setUbicacion(dto.getUbicacion());
    	consultorio.setPiso(dto.getPiso());
    	
    	return consultorio;
	}	
    
    @Override
    public ConsultorioResponseDto crearConsultorio(ConsultorioRequestDto dto) {
        // Verificar duplicado
        repository.findByIdentificadorLegibleIgnoreCase(dto.getIdentificadorLegible())
                .ifPresent(c -> {
                    throw new IllegalArgumentException("El identificadorLegible ya existe");
                });

        Consultorio consultorio = mapper.fromDto(dto);
        Consultorio guardado = repository.save(consultorio);

        return mapper.toResponseDto(guardado);
    }

	@Override
	public Optional<Consultorio> findByIdentificadorLegibleIgnoreCase(String identificadorLegible) {
		// TODO Auto-generated method stub
		 return repository.findByIdentificadorLegibleIgnoreCase(identificadorLegible);
	}
	
	

}
