package com.imb2025.smedico.service.jpa;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.mapper.TurnoMapper;
import com.imb2025.smedico.dto.request.TurnoRequestDto.TurnoRequestDto;
import com.imb2025.smedico.dto.response.TurnoResponseDto.TurnoResponseDto;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.ITurnoService;

@Service
public class TurnoServiceImpl implements ITurnoService {

    @Autowired
    private TurnoRepository repo;

    @Autowired
    private PacienteRepository pacienteRepository;

    @Autowired
    private MedicoRepository medicoRepository;

    @Autowired
    private EstadoTurnoRepository estadoTurnoRepository;

    @Override
    public List<TurnoResponseDto> findAll() {
        return repo.findAll().stream()
                .map(TurnoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public TurnoResponseDto findById(Long id) {
        Turno turno = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id " + id));
        return TurnoMapper.toResponseDto(turno);
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        Turno turno = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno con ID " + id + " no encontrado"));
        repo.delete(turno);
    }

    @Override
    public TurnoResponseDto create(TurnoRequestDto dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado"));
        EstadoTurno estado = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de turno no encontrado"));

        Turno turno = TurnoMapper.fromDto(dto, paciente, medico, estado);
        Turno saved = repo.save(turno);

        return TurnoMapper.toResponseDto(saved);
    }

    @Override
    public TurnoResponseDto update(Long id, TurnoRequestDto dto) {
        Turno turnoExistente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Turno con ID " + id + " no encontrado"));

        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Paciente no encontrado"));
        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new ResourceNotFoundException("Médico no encontrado"));
        EstadoTurno estado = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
                .orElseThrow(() -> new ResourceNotFoundException("Estado de turno no encontrado"));

        TurnoMapper.updateEntityFromDto(dto, turnoExistente, paciente, medico, estado);
        Turno updated = repo.save(turnoExistente);

        return TurnoMapper.toResponseDto(updated);
    }

    @Override
    public List<TurnoResponseDto> findByFecha(LocalDate fecha) {
        return repo.findByFecha(fecha).stream()
                .map(TurnoMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public long countByFecha(LocalDate fecha) {
        return repo.countByFecha(fecha);
    }
}
