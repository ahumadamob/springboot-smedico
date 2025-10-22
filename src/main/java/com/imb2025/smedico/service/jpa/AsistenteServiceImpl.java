package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.request.AsistenteRequestDto;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.AsistenteRepository;
import com.imb2025.smedico.service.IAsistenteService;

@Service
public class AsistenteServiceImpl implements IAsistenteService {

    @Autowired
    private AsistenteRepository repo;

    @Override
    public List<Asistente> findAll() {
        return repo.findAll();
    }

    @Override
    public Asistente findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Asistente no encontrado con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public Asistente create(Asistente asistente) {
        return repo.save(asistente);
    }

    @Override
    public Asistente update(Long id, Asistente asistente) {
        Asistente existente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede actualizar. Asistente con id " + id + " no existe."));

        existente.setApellido(asistente.getApellido());
        existente.setNombre(asistente.getNombre());
        existente.setEmail(asistente.getEmail());
        existente.setTelefono(asistente.getTelefono());
        existente.setDni(asistente.getDni());

        existente.setEsSupervisor(asistente.getEsSupervisor());

        return repo.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se puede eliminar. Asistente con id " + id + " no existe.");
        }
        repo.deleteById(id);
    }
     
    // Métodos mágicos
    @Override
    public List<Asistente> findByApellido(String apellido) {
        return repo.findByApellido(apellido);
    }

    @Override
    public Long countByNombre(String nombre) {
        return repo.countByNombre(nombre);
    }

    @Override
    public List<Asistente> findSupervisores() {
        return repo.findByEsSupervisorTrue();
    }

    @Override
    public List<Asistente> findNoSupervisores() {
        return repo.findByEsSupervisorFalse();
    }

}