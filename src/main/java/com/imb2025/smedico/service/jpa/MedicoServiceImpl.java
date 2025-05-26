package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.service.IMedicoService;

@Service
public class MedicoServiceImpl implements IMedicoService {

    @Autowired
    private MedicoRepository repo;

    @Override
    public List<Medico> findAll() {
        return repo.findAll();
    }

    @Override
    public Medico findById(Long id) {
        Optional<Medico> 
        opt = repo.findById(id);
        if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
    }

    @Override
    public Medico save(Medico medico) {
        return repo.save(medico);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
}

