package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.Consultorio;
import com.imb2025.smedico.repository.ConsultorioRepository;
import com.imb2025.smedico.service.IConsultorioService;

@Service
public class ConsultorioServiceImpl implements IConsultorioService {

	@Autowired
	private ConsultorioRepository repository;
	
	//Crear y guardar nuevo repositorio
	@Override
	public Consultorio guardarConsultorio(Consultorio consultorio) {
		return repository.save(consultorio);
	}
	
	//Buscar por id
	 @Override
	 public Consultorio buscarPorId(Long id) {
		 Optional<Consultorio> opt;
			opt = repository.findById(id);
			if(opt.isPresent()) {
				return opt.get();
			}else {
				return null;
		 }
	 }
	
	//Buscar por nombre
	@Override
    public List<Consultorio> buscarPorNombre(String nombre) {
        return repository.findByNombre(nombre);
    }
	
	//Listar todos
	@Override
    public List<Consultorio> listarTodos() {
	    return repository.findAll();
	}
	
	// Eliminar por ID
    @Override
    public void eliminarPorId(Long id) {
        repository.deleteById(id);
    }
    
    //Eliminar por nombre
    @Override
    public void eliminarPorNombre(String nombre) {
        repository.deleteByNombre(nombre);
    }
	
}
