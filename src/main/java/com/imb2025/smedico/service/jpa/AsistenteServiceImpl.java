package com.imb2025.smedico.service.jpa;
//Implementacion

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.dto.AsistenteRequestDto;
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
                    "EstadoTurno no encontrado con id " + id));
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
        if (!repo.existsById(id)) {
            throw new RuntimeException("Asistente con ID " + id + " no existe.");
        }

        Asistente existente = findById(id);
        existente.setNombre(asistente.getNombre());
        existente.setEmail(asistente.getEmail());
        existente.setTelefono(asistente.getTelefono());
        existente.setDni(asistente.getDni());
        return repo.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("Asistente con ID " + id + " no existe.");
        }
        repo.deleteById(id);
    }

    @Override
    public Asistente fromDto(AsistenteRequestDto dto) {
        Asistente asistente = new Asistente();
        asistente.setApellido(dto.getApellido());
        asistente.setDni(dto.getDni());
        asistente.setEmail(dto.getEmail());
        asistente.setNombre(dto.getNombre());
        asistente.setTelefono(dto.getTelefono());
        return asistente;
    }
}
