package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.PacienteRequestDTO;
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
    public boolean existsById(Long id) {
        return pacienteRepository.existsById(id);
    }

    @Override
    public Paciente save(Paciente paciente) {
        try {
            return pacienteRepository.save(paciente);
        } catch (Exception e) {
            throw new RuntimeException("Error al guardar el paciente: " + e.getMessage());
        }
    }

    @Override
    public Paciente create(Paciente paciente) {
        return save(paciente);
    }

    @Override
    public Paciente update(Long id, Paciente paciente) throws Exception {
        if (!existsById(id)) {
            throw new Exception("Paciente no encontrado con id: " + id);
        }
        paciente.setId(id);
        return pacienteRepository.save(paciente);
    }

    @Override
    public void deleteById(Long id) {
        if (!existsById(id)) {
            throw new IllegalArgumentException("No se puede eliminar: Paciente no encontrado con ID: " + id);
        }
        pacienteRepository.deleteById(id);
    }

    @Override
    public Paciente fromDto(PacienteRequestDTO requestDTO) {
        Paciente paciente = new Paciente();
        paciente.setNombre(requestDTO.getNombre());
        paciente.setApellido(requestDTO.getApellido());
        paciente.setDni(requestDTO.getDni());
        paciente.setEmail(requestDTO.getEmail());
        paciente.setTelefono(requestDTO.getTelefono());
        paciente.setFechaNacimiento(requestDTO.getFechaNacimiento());
        return paciente;
    }
}