package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.repository.DiagnosticoRepository;
import com.imb2025.smedico.service.IDiagnosticoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    @Autowired
    private DiagnosticoRepository repo;

    @Override
    public List<Diagnostico> findAll() {
        return repo.findAll();
    }

    @Override
    public Optional<Diagnostico> findById(Long id) {
        return repo.findById(id);
    }

    @Override
    public Diagnostico save(Diagnostico diagnostico) {
        return repo.save(diagnostico);
    }

    @Override
    public Diagnostico update(Long id, Diagnostico diagnostico) {
        diagnostico.setId(id); // Asegurarse que el ID sea el correcto
        return repo.save(diagnostico); // En JPA, save actúa como update si el ID ya existe
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
