package com.imb2025.smedico.service.jpa;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.AsistenteEntity;
import com.imb2025.smedico.repository.AsistenteRepository;
import com.imb2025.smedico.service.Iasistente;

@Service
public class AsistenteImpl implements Iasistente {

    @Autowired
    private AsistenteRepository asistenteRepo;

    @Override
    public List<AsistenteEntity> findAll() {
        return asistenteRepo.findAll();
    }

    @Override
    public AsistenteEntity findById(Long id) {
        Optional<AsistenteEntity> asistente = asistenteRepo.findById(id);
        return asistente.orElse(null);
    }

    public AsistenteEntity update(Long id, AsistenteEntity asistenteActualizado) {
        AsistenteEntity existente = asistenteRepo.findById(id)
            .orElseThrow(() -> new RuntimeException("Asistente no encontrado"));

        existente.setNombre(asistenteActualizado.getNombre());
        existente.setEmail(asistenteActualizado.getEmail());
        existente.setTelefono(asistenteActualizado.getTelefono());
        existente.setDni(asistenteActualizado.getDni());

        return asistenteRepo.save(existente);
    }
    
    @Override
    public AsistenteEntity save(AsistenteEntity asistenteEntity) {
        return asistenteRepo.save(asistenteEntity);
    }

    @Override
    public void deleteById(Long id) {
        asistenteRepo.deleteById(id);
    }
}
