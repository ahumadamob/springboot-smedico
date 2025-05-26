package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.ITurnoService;

@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    private TurnoRepository repo;

    @Override
    public List<Turno> findAll() {
        return repo.findAll();
    }

    @Override
    public Turno findById(Long id) {
        Optional<Turno> opt = repo.findById(id);
        return opt.orElse(null);
    }

    @Override
    public Turno save(Turno turno) {
        return repo.save(turno);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}
