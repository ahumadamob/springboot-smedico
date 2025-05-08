package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.PacienteService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PacienteServiceImpl implements PacienteService {

    private final PacienteRepository pacienteRepository;

    public PacienteServiceImpl(PacienteRepository pacienteRepository) {
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public List<Paciente> findAll() {
        return pacienteRepository.findAll();
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        return pacienteRepository.findById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        return pacienteRepository.save(paciente);
    }

    @Override
    public Paciente update(Long id, Paciente paciente) {
        return pacienteRepository.findById(id)
                .map(existing -> {
                    existing.setNombre(paciente.getNombre());
                    existing.setApellido(paciente.getApellido());
                    existing.setDni(paciente.getDni());
                    existing.setEmail(paciente.getEmail());
                    existing.setFechaNacimiento(paciente.getFechaNacimiento());
                    existing.setTelefono(paciente.getTelefono());
                    return pacienteRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));
    }

    @Override
    public void deleteById(Long id) {
        pacienteRepository.deleteById(id);
    }
}