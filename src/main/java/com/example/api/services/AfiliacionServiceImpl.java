package com.example.api.services;

import com.example.api.entity.Afiliacion;
import com.example.api.repository.AfiliacionRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AfiliacionServiceImpl implements AfiliacionService {

    @Autowired
    private AfiliacionRepository repository;

    @Override
    public List<Afiliacion> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<Afiliacion> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public Afiliacion save(Afiliacion afiliacion) {
        return repository.save(afiliacion);
    }

    @Override
    public Afiliacion update(Long id, Afiliacion afiliacion) {
        return repository.findById(id)
                .map(existing -> {
                    existing.setPacienteId(afiliacion.getPacienteId());
                    existing.setObraSocialId(afiliacion.getObraSocialId());
                    existing.setNumeroAfiliado(afiliacion.getNumeroAfiliado());
                    existing.setFechaVigenciaDesde(afiliacion.getFechaVigenciaDesde());
                    existing.setFechaHasta(afiliacion.getFechaHasta());
                    return repository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Afiliación no encontrada con ID: " + id));
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

