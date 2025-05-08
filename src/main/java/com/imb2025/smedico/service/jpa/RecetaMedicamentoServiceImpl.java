package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.RecetaMedicamento;
import com.imb2025.smedico.repository.RecetaMedicamentoRepository;
import com.imb2025.smedico.service.RecetaMedicamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecetaMedicamentoServiceImpl implements RecetaMedicamentoService {

    @Autowired
    private RecetaMedicamentoRepository repository;

    @Override
    public List<RecetaMedicamento> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<RecetaMedicamento> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public RecetaMedicamento save(RecetaMedicamento recetaMedicamento) {
        return repository.save(recetaMedicamento);
    }

    @Override
    public RecetaMedicamento update(Long id, RecetaMedicamento recetaMedicamento) {
        recetaMedicamento.setId(id);
        return repository.save(recetaMedicamento);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
