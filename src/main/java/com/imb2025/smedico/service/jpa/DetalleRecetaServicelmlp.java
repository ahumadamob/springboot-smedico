package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.repository.DetalleRecetaRepository;
import com.imb2025.smedico.service.DetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleRecetaServicelmlp implements DetalleRecetaService {

    @Autowired
    private DetalleRecetaRepository repository;

    @Override
    public List<DetalleReceta> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<DetalleReceta> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public DetalleReceta save(DetalleReceta recetaMedicamento) {
        return repository.save(recetaMedicamento);
    }

    @Override
    public DetalleReceta update(Long id, DetalleReceta recetaMedicamento) {
        recetaMedicamento.setId(id);
        return repository.save(recetaMedicamento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
