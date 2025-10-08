package com.imb2025.smedico.service.jpa;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.dto.TurnoRequestDto;
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
    public List<Turno> findAll() {
        return repo.findAll();
    }

    @Override
    public Turno findById(Long id) {
    	return repo.findById(id)
    		    .orElseThrow(() -> new ResourceNotFoundException(
    		        "Entidad no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }


    @Override
    public void deleteById(Long id) {
        Turno existente = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Turno con ID " + id + " no encontrado"));
        repo.delete(existente);
    }
    
    @Override
    public Turno create(Turno turno) {
        return repo.save(turno);
    }

    @Override
    public Turno update(Long id, Turno turno) {
        Turno turnoExistente = repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Turno con ID " + id + " no encontrado"));

        turnoExistente.setFecha(turno.getFecha());
        turnoExistente.setHora(turno.getHora());
        turnoExistente.setPaciente(turno.getPaciente());
        turnoExistente.setMedico(turno.getMedico());
        turnoExistente.setEstadoTurno(turno.getEstadoTurno());

        return repo.save(turnoExistente);
    }
    
    @Override
    public Turno fromDto(TurnoRequestDto dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
            .orElseThrow(() -> new ResourceNotFoundException("Paciente con ID " + dto.getPacienteId() + " no encontrado"));
        Medico medico = medicoRepository.findById(dto.getMedicoId())
            .orElseThrow(() -> new ResourceNotFoundException("Médico con ID " + dto.getMedicoId() + " no encontrado"));
        EstadoTurno estado = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
            .orElseThrow(() -> new ResourceNotFoundException("Estado de turno con ID " + dto.getEstadoTurnoId() + " no encontrado"));

        Turno turno = new Turno();
        turno.setEstadoTurno(estado);
        turno.setFecha(dto.getFecha());
        turno.setHora(dto.getHora());
        turno.setMedico(medico);
        turno.setPaciente(paciente);
        return turno;
    }

    
    @Override
    public List<Turno> findByFecha(LocalDate fecha) {
        return repo.findByFecha(fecha);
    }
    @Override
    public long countByFecha(LocalDate fecha) {
        return repo.countByFecha(fecha);
    }

    
  
}
