package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.DetalleReceta;
import com.imb2025.smedico.repository.DetalleRecetaRepository;
import com.imb2025.smedico.service.IDetalleRecetaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DetalleRecetaServicelmpl implements IDetalleRecetaService {

    @Autowired
    private DetalleRecetaRepository repository;

    @Override
    public List<DetalleReceta> findAll() {
        return repository.findAll();
    }

    @Override
    public DetalleReceta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public DetalleReceta save(DetalleReceta recetaMedicamento) {
        return repository.save(recetaMedicamento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
