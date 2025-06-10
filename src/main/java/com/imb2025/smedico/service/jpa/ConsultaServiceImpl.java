package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.ConsultaRequestDTO;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Paciente;
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
    public Consulta save(ConsultaRequestDTO dto) {
        try {
            Consulta consulta = new Consulta();
            consulta.setFecha(dto.getFecha());
            consulta.setTurnoId(dto.getTurnoId());
            consulta.setDuracionMin(dto.getDuracionMin());
            consulta.setComentarios(dto.getComentarios());

            return repository.save(consulta);
        } catch (Exception e) {
            System.err.println(" Error al guardar consulta: " + e.getMessage());
            e.printStackTrace();
            throw new RuntimeException("Error al guardar consulta");
        }
    }


    @Override
    public Consulta update(Long id, ConsultaRequestDTO dto) {
        Consulta existing = repository.findById(id).orElseThrow(() -> new RuntimeException("Consulta no encontrada"));
        existing.setFecha(dto.getFecha());
        existing.setTurnoId(dto.getTurnoId());
        existing.setDuracionMin(dto.getDuracionMin());
        existing.setComentarios(dto.getComentarios());
        return repository.save(existing);
    }
    
    @Override
    public Consulta fromDto(ConsultaRequestDTO dto) throws Exception {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new Exception("Paciente no encontrado con ID: " + dto.getPacienteId()));

        Consulta consulta = new Consulta();
        consulta.setFecha(dto.getFecha());
        consulta.setComentarios(dto.getComentarios());
        consulta.setDuracionMin(dto.getDuracionMin());
        consulta.setTurnoId(dto.getTurnoId());
        consulta.setPaciente(paciente); 

        return consulta;
    }
    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }
}
