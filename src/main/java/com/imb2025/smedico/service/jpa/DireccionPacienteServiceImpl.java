package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.DireccionPacienteRequestDto;
import com.imb2025.smedico.entity.DireccionPaciente;
import com.imb2025.smedico.repository.DireccionPacienteRepository;
import com.imb2025.smedico.service.IDireccionPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DireccionPacienteServiceImpl implements IDireccionPacienteService {

    @Autowired
    private DireccionPacienteRepository direccionPacienteRepository;

    @Override
    public List<DireccionPaciente> findAll() {
        return direccionPacienteRepository.findAll();
    }

    @Override
    public DireccionPaciente findById(Long id) {
        return direccionPacienteRepository.findById(id).orElse(null);
    }

    @Override
    public DireccionPaciente create(DireccionPaciente direccionPaciente) throws Exception {
        return direccionPacienteRepository.save(direccionPaciente);
    }

    @Override
    public DireccionPaciente update(Long id, DireccionPaciente direccionPaciente) throws Exception {
        if (!direccionPacienteRepository.existsById(id)) {
            throw new RuntimeException("No se encontró la dirección con ID: " + id);
        }
        direccionPaciente.setId(id);
        return direccionPacienteRepository.save(direccionPaciente);
    }

    @Override
    public void deleteById(Long id) {
        if (!direccionPacienteRepository.existsById(id)) {
            throw new RuntimeException("No se puede eliminar. No existe dirección con ID: " + id);
        }
        direccionPacienteRepository.deleteById(id);
    }

    @Override
    public DireccionPaciente fromDto(DireccionPacienteRequestDto dto) throws Exception {
        DireccionPaciente direccion = new DireccionPaciente();
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setLocalidad(dto.getLocalidad());
        direccion.setProvincia(dto.getProvincia());
        direccion.setCcpp(dto.getCcpp());
        return direccion;
    }

    @Override
    public boolean existsById(Long id) {
        return direccionPacienteRepository.existsById(id);
    }
}
