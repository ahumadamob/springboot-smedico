package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.repository.DetalleRecetaRepository;
import com.imb2025.smedico.service.IDetalleRecetaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleRecetaServiceImpl implements IDetalleRecetaService {

    @Autowired
    private DetalleRecetaRepository repository;

  
    @Override
    public List<DetalleReceta> findAll() {
       return repository.findAll();
    }

    @Override
    public DetalleReceta findById(Long id) {
    	return repository.findById(id)
    		  .orElseThrow(() -> new ResourceNotFoundException("DetalleReceta no encontrada con id " + id));
    }

    @Override
    public DetalleReceta create (DetalleReceta detalleReceta) {
        
    	return repository.save(detalleReceta);
    }

    @Override
    public DetalleReceta update(Long id, DetalleReceta detalleReceta) {
    	if(repository.existsById(id)) {
    		
    		detalleReceta.setId(id);
    		return repository.save(detalleReceta);
    	}
    	throw new ResourceNotFoundException("Detalle Receta con el id: " + id + "No encontrado");
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("DetalleReceta no encontrada con id " + id);
        }
        repository.deleteById(id);
    }

	@Override
	public List<DetalleReceta> findByControladoTrue() {
		return repository.findByControladoTrue();
	}

	@Override
	public List<DetalleReceta> findByControladoFalse() {
		return repository.findByControladoFalse();
	}

	@Override
	public List<DetalleReceta> findByRecetaId(Long recetaId) {
		return repository.findByRecetaId(recetaId);
	}

	@Override
	public Long countByMedicamentoId(Long medicamentoId) {
		return repository.countByMedicamentoId(medicamentoId);
	}
}

 


