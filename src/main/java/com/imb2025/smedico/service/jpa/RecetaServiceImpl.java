package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.RecetaRepository;
import com.imb2025.smedico.service.IRecetaService;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecetaServiceImpl implements IRecetaService {


    @Autowired
    private RecetaRepository repo;

    @Override
    public List<Receta> findAll() {
        return repo.findAll();
    }

    @Override
    public Receta findById(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResourceNotFoundException(
                       "Entidad no encontrada con id " + id));
    }

    @Override
    public Receta create(Receta receta) {
    	
    	if(receta.getSeveridad()>10 || receta.getSeveridad()==0) {
    		
        	throw new ResourceNotFoundException("La severidad debe estar entre 1 y 10");
		
    	}
    	
        return repo.save(receta);
    }

    @Override
    public Receta update(Long id, Receta recetaNueva) {

        Receta existente = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "Receta con ID " + id + " no existe"));

        // actualizar campos
        existente.setFecha(recetaNueva.getFecha());
        existente.setObservaciones(recetaNueva.getObservaciones());
        existente.setMedico(recetaNueva.getMedico());
        existente.setPaciente(recetaNueva.getPaciente());

        return repo.save(existente);
    }


    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("Receta con ID " + id + " no existe");
        }
        repo.deleteById(id);
    }


    @Override
    public List<Receta> findByFecha(LocalDate fecha) {
        return repo.findByFecha(fecha);
    }

    @Override
    public long countByFecha(LocalDate fecha) {
        return repo.countByFecha(fecha);
        }
    @Override
    public List<Receta> findBySeveridadGreaterThanEqual (int valor) {
    	//if (valor <=8) {
	      //  throw new ResourceNotFoundException("La prioridad debe ser mayor a 7");
	        //}
      return repo.findBySeveridadGreaterThanEqual(valor);

    }
    @Override
    public List<Receta> findBySeveridadLessThanEqual (int valor) {
    	//if (valor >4)  {
	      //  throw new ResourceNotFoundException("La prioridad debe ser menor a 4");
	    //}
      return repo.findBySeveridadLessThanEqual(valor);

    }
    
}
