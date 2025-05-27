package com.imb2025.smedico.service.jpa;
//Implementacion
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.AsistenteRequestDTO;
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
        return asistenteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Asistente con ID " + id + " no encontrado."));
    }

    @Override
    public Asistente create(AsistenteRequestDTO dto) {
        try {
            Asistente nuevo = new Asistente();
            nuevo.setNombre(dto.getNombre());
            nuevo.setEmail(dto.getEmail());
            nuevo.setTelefono(dto.getTelefono());
            nuevo.setDni(dto.getDni());
            return asistenteRepo.save(nuevo);
        } catch (Exception e) {
            throw new RuntimeException("Error al crear el asistente: " + e.getMessage());
        }
    }

    @Override
    public Asistente update(Long id, AsistenteRequestDTO dto) {
        try {
            Asistente existente = asistenteRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Asistente con ID " + id + " no encontrado."));

            existente.setNombre(dto.getNombre());
            existente.setEmail(dto.getEmail());
            existente.setTelefono(dto.getTelefono());
            existente.setDni(dto.getDni());

            return asistenteRepo.save(existente);
        } catch (Exception e) {
            throw new RuntimeException("Error al actualizar el asistente: " + e.getMessage());
        }
    }

    @Override
    public void deleteById(Long id) {
        if (!asistenteRepo.existsById(id)) {
            throw new RuntimeException("No se puede eliminar: asistente con ID " + id + " no existe.");
        }
        asistenteRepo.deleteById(id);
    }
}
