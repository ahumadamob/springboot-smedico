package com.imb2025.smedico.service.jpa;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.repository.AsistenteRepository;
import com.imb2025.smedico.service.IAsistenteService;

@Service
public class AsistenteServiceImpl implements IAsistenteService {

    @Autowired
    private AsistenteRepository asistenteRepo;

    @Override
    public List<Asistente> findAll() {
        return asistenteRepo.findAll();
    }

    @Override
    public Asistente findById(Long id) {
        Optional<Asistente> asistente = asistenteRepo.findById(id);
        return asistente.orElse(null);
    }

    public Asistente update(Long id, Asistente asistenteActualizado) {
        Asistente existente = asistenteRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Asistente no encontrado"));

        existente.setNombre(asistenteActualizado.getNombre());
        existente.setEmail(asistenteActualizado.getEmail());
        existente.setTelefono(asistenteActualizado.getTelefono());
        existente.setDni(asistenteActualizado.getDni());

        return asistenteRepo.save(existente);
    }
    
    @Override
    public Asistente save(Asistente asistenteEntity) {
        return asistenteRepo.save(asistenteEntity);
    }

    @Override
    public void deleteById(Long id) {
        asistenteRepo.deleteById(id);
    }
}
