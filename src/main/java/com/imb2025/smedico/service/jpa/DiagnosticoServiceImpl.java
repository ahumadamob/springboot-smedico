package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.Diagnostico;
import com.imb2025.smedico.repository.DiagnosticoRepository;
import com.imb2025.smedico.service.IDiagnosticoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DiagnosticoServiceImpl implements IDiagnosticoService {

    @Autowired
    private DiagnosticoRepository repo;

    @Override
    public List<Diagnostico> findAll() {
        return repo.findAll();
    }

    @Override
    public Diagnostico findById(Long id) {
        return repo.findById(id).orElse(null);
    }

    @Override
    public Diagnostico save(Diagnostico diagnostico) {
        return repo.save(diagnostico);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
