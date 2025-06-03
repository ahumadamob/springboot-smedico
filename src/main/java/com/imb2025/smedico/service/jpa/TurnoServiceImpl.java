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

    public Turno create(TurnoRequestDTO dto) {
	    try {
	    	Turno turno = fromDto(dto);
	        return repo.save(turno);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al crear el Turno: " + e.getMessage(), e);
	    }
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
	public Turno update(Long id, TurnoRequestDTO dto) throws Exception {
		 Turno turnoExistente = repo.findById(id)
	        .orElseThrow(() -> new Exception("Turno con ID " + id + " no encontrada"));
	    Medico medico = medicoRepository.findById(dto.getMedicoId())
	        .orElseThrow(() -> new Exception("Medico con ID " + dto.getMedicoId() + " no encontrado"));
	    Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
	        .orElseThrow(() -> new Exception("Paciente con ID " + dto.getPacienteId() + " no encontrado"));
	    EstadoTurno estadoTurno = estadoTurnoRepository.findById(dto.getEstadoTurnoId())
		        .orElseThrow(() -> new Exception("Estado Turno no encontrado"));
	    turnoExistente . setFecha ( dto . getFecha ());
        turnoExistente . setHora ( dto . getHora ());
        turnoExistente . setPaciente ( paciente );
        turnoExistente . setMedico ( medico );
        turnoExistente . setEstadoTurno ( estadoTurno );

        return  repo . save ( turnoExistente );
	}


    
  
}
