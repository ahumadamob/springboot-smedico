package com.imb2025.smedico.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.EstudioEntity;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.service.jpa.IEstudioService;
@Service
public  class EstudioServiceImpl implements IEstudioService {
    
    @Autowired
    private EstudioRepository repo;

    @Override
    public List<EstudioEntity> findAll() {
        return repo.findAll();
    }

    @Override
    public EstudioEntity findById(Long id) {
        Optional<EstudioEntity> opt = repo.findById(id);
        return opt.orElse(null);
    }

    @Override
    public EstudioEntity save(EstudioEntity estudio) {
        return repo.save(estudio);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
