package com.imb2025.smedico.service.impl;

import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IObraSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repository;

    @Override
    public List<ObraSocial> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ObraSocial> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ObraSocial save(ObraSocial obraSocial) {
        return repository.save(obraSocial);
    }

    @Override
    public ObraSocial update(Long id, ObraSocial obraSocial) {
        if (repository.existsById(id)) {
            obraSocial.setId(id);
            return repository.save(obraSocial);
        } else {
            return null;
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}

