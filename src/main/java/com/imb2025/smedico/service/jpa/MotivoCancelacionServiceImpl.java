package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.MotivoCancelacion;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.MotivoCancelacionRepository;
import com.imb2025.smedico.service.IMotivoCancelacionService;

@Service
public class MotivoCancelacionServiceImpl implements IMotivoCancelacionService {

	@Autowired
    private MotivoCancelacionRepository repo;

    @Override
    public List<MotivoCancelacion> findAll() {
        return repo.findAll();
    }

    @Override
    public MotivoCancelacion findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "MotivoCancelacion no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public MotivoCancelacion create(MotivoCancelacion motivoCancelacion) {
        return repo.save(motivoCancelacion);
    }

    @Override
    public MotivoCancelacion update(Long id, MotivoCancelacion motivoCancelacion) {
        MotivoCancelacion existente = repo.findById(id)
            .orElseThrow(() ->
                new ResourceNotFoundException("MotivoCancelacion con ID " + id + " no existe")
            );

        existente.setNombre(motivoCancelacion.getNombre());
        existente.setDescripcion(motivoCancelacion.getDescripcion());
        //En caso de agregar campos extras agregar aca el update
        return repo.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException("MotivoCancelacion con ID " + id + " no existe");
        }
        repo.deleteById(id);
    }
    
    @Override
    public List<MotivoCancelacion> findByNombre(String nombre) {
        return repo.findByNombreContainingIgnoreCase(nombre);
    }

    @Override
    public long countByDescripcion(String descripcion) {
        return repo.countByDescripcion(descripcion);
    }
    
    @Override
    public List<MotivoCancelacion> findByAliasContainingIgnoreCase(String texto) {
        return repo.findByAliasContainingIgnoreCase(texto);
    }
}
