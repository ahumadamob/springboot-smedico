package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.DireccionPacienteRequestDTO;
import com.imb2025.smedico.entity.DireccionPaciente;
import com.imb2025.smedico.repository.DireccionPacienteRepository;
import com.imb2025.smedico.service.DireccionPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DireccionPacienteServiceImpl implements DireccionPacienteService {

    @Autowired
    private DireccionPacienteRepository direccionPacienteRepository;

    @Override
    public List<DireccionPaciente> findAll() {
        return direccionPacienteRepository.findAll();
    }

    @Override
    public Optional<DireccionPaciente> findById(Long id) {
        return direccionPacienteRepository.findById(id);
    }

    @Override
    public DireccionPaciente save(DireccionPaciente direccionPaciente) {
        return direccionPacienteRepository.save(direccionPaciente);
    }

    @Override
    public DireccionPaciente update(Long id, DireccionPacienteRequestDTO dto) {
        DireccionPaciente direccion = direccionPacienteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Dirección no encontrada con ID: " + id));

        mapearDTO(direccion, dto);
        return direccionPacienteRepository.save(direccion);
    }

    @Override
    public void deleteById(Long id) {
        direccionPacienteRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return direccionPacienteRepository.existsById(id);
    }

    @Override
    public DireccionPaciente fromDto(DireccionPacienteRequestDTO dto) {
        DireccionPaciente direccion = new DireccionPaciente();
        mapearDTO(direccion, dto);
        return direccion;
    }

    // Mapea los campos del DTO a la entidad
    private void mapearDTO(DireccionPaciente direccion, DireccionPacienteRequestDTO dto) {
        direccion.setCalle(dto.getCalle());
        direccion.setNumero(dto.getNumero());
        direccion.setLocalidad(dto.getLocalidad());
        direccion.setProvincia(dto.getProvincia());
        direccion.setCcpp(dto.getCcpp());
        // No hay relación pacienteId, así que no seteamos nada aquí
    }
}
