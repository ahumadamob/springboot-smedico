package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.TurnoRequestDTO;
import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Turno;
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
            .orElseThrow(() -> new RuntimeException("Turno con ID " + id + " no encontrado"));
    }


    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new RuntimeException("No existe un turno con ID " + id);
        }
        repo.deleteById(id);
    }
    
    @Override
    public Turno create(Turno turno) {
        return repo.save(turno);
    }

    @Override
    public Turno update(Long id, Turno turno) throws Exception {
        Turno turnoExistente = repo.findById(id)
            .orElseThrow(() -> new Exception("Turno con ID " + id + " no encontrado"));
        
        turnoExistente.setFecha(turno.getFecha());
        turnoExistente.setHora(turno.getHora());
        turnoExistente.setPaciente(turno.getPaciente());
        turnoExistente.setMedico(turno.getMedico());
        turnoExistente.setEstadoTurno(turno.getEstadoTurno());

        return repo.save(turnoExistente);
    }

    
  
}
