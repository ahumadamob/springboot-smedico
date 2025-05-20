package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IPacienteService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteServiceImpl implements IPacienteService {


    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Paciente findById(Long id) {
        return pacienteRepository.findById(id).orElse(null);
    }
    
    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }
  
    @Override
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
        
    }
}