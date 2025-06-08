package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.repository.DetalleRecetaRepository;
import com.imb2025.smedico.service.IDetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class DetalleRecetaServicelmpl implements IDetalleRecetaService {

    @Autowired
    private DetalleRecetaRepository repository;

    @Override
    public List<DetalleReceta> findAll() {
        return repository.findAll();
    }

    
    @Override
    public DetalleReceta findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("DetalleReceta no encontrado con id: " + id));
    }


    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

    @Override
    public DetalleReceta create(DetalleReceta detalleReceta) {
        return repository.save(detalleReceta);
    }

 
    @Override
    public DetalleReceta update(Long id, DetalleReceta detalleReceta) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("No se puede actualizar. DetalleReceta no encontrado con id: " + id);
        }
        detalleReceta.setId(id);
        return repository.save(detalleReceta);
    }

    
    @Override
    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new NoSuchElementException("No se puede eliminar. DetalleReceta no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
}
