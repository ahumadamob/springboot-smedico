package com.imb2025.smedico.service.jpa;
//Implementacion

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.dto.AsistenteRequestDTO;
import com.imb2025.smedico.entity.Asistente;
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
                .orElseThrow(() ->
                    new RuntimeException("Asistente con ID " + id + " no encontrado."));
    }

    @Override
    public boolean existsById(Long id) {         
        return repo.existsById(id);
    }

    @Override
    public Asistente create(AsistenteRequestDTO dto) {
        return repo.save(fromDto(dto));
    }

    @Override
    public Asistente update(Long id, AsistenteRequestDTO dto) {
        if (!repo.existsById(id)) { 
            throw new RuntimeException("Asistente con ID " + id + " no existe.");
        }
        Asistente existente = findById(id);     
        existente.setNombre(dto.getNombre());
        existente.setEmail(dto.getEmail());
        existente.setTelefono(dto.getTelefono());
        existente.setDni(dto.getDni());
        return repo.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Asistente con ID " + id + " no existe.");
        }
        repo.deleteById(id);
    }

    /* ---------- Conversión DTO -> Entidad ---------- */
    private static Asistente fromDto(AsistenteRequestDTO dto) {
        Asistente a = new Asistente();
        a.setNombre(dto.getNombre());
        a.setEmail(dto.getEmail());
        a.setTelefono(dto.getTelefono());
        a.setDni(dto.getDni());
        return a;
    }
}
