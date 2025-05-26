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
        Optional<Turno> opt;
        opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
    }

    @Override
    public Turno save(Turno turno) {
        return repo.save(turno);
    }

    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }
    
    @Override
    public Turno fromDto(TurnoRequestDTO dto) throws Exception{
	    Paciente paciente= pacienteRepository.findById(dto.getPacienteId())
		        .orElseThrow(() -> new Exception("Paciente no encontrado"));
	    
	    Medico medico = medicoRepository.findById(dto.getMedicoId())
		        .orElseThrow(() -> new Exception("Medico no encontrado"));
	    
	    EstadoTurno estadoTurno = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
		        .orElseThrow(() -> new Exception("Estado Turno no encontrado"));
	    
	    Turno turno = new Turno(dto.getFecha(), dto.getHora(), paciente, medico, estadoTurno);
		return turno;

	    
    }
    
    @Override
    public Turno updateFromDto(Turno turnoExistente, TurnoRequestDTO dto) throws Exception {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new Exception("Paciente no encontrado"));

        Medico medico = medicoRepository.findById(dto.getMedicoId())
                .orElseThrow(() -> new Exception("Médico no encontrado"));

        EstadoTurno estado = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
                .orElseThrow(() -> new Exception("Estado del turno no encontrado"));

        // Seteamos los nuevos valores al objeto existente
        turnoExistente.setFecha(dto.getFecha());
        turnoExistente.setHora(dto.getHora());
        turnoExistente.setPaciente(paciente);
        turnoExistente.setMedico(medico);
        turnoExistente.setEstadoTurno(estado);

        return repo.save(turnoExistente);
    }
    
    @Override
    public Turno fromDto(TurnoRequestDTO dto, Long id) throws Exception {
        Turno turno = fromDto(dto);  // reutilizo el método anterior
        turno.setId(id);            // seteo el id para actualizarlo
        return turno;
    }
}
