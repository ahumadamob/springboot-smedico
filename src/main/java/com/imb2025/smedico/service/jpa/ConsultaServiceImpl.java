package com.imb2025.smedico.service.jpa;


import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.service.IConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Override
    public List<Consulta> findAll() {
        return repository.findAll();
    }

    @Override
    public Consulta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Consulta save(Consulta consulta) {
        return repository.save(consulta);
    }

    @Override
    public Consulta update(Consulta consulta) {
        return repository.save(consulta);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

