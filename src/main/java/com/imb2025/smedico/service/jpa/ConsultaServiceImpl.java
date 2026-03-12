package com.imb2025.smedico.service.jpa;
import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.IConsultaService;
@Service
@Transactional
public class ConsultaServiceImpl implements IConsultaService {

    private final ConsultaRepository consultaRepository;
    private final TurnoRepository turnoRepository;

    public ConsultaServiceImpl(ConsultaRepository consultaRepository, TurnoRepository turnoRepository) {
        this.consultaRepository = consultaRepository;
        this.turnoRepository = turnoRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Consulta> findAll() {
        return consultaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Consulta findById(Long id) {
        return consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id " + id));
    }

    private String normalizarDescripcion(String valor) {
    	return valor.trim().replaceAll("\\s+"," ");
    }
    
    @Override
    public Consulta create(Consulta nueva, Long turnoId) {
    	normalizarDescripcion(nueva.getDescripcionCorta());
        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id " + turnoId));

        if (consultaRepository.existsByTurno_Id(turnoId)) {
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }

        nueva.setTurno(turno);
        return consultaRepository.save(nueva);
    }

    @Override
    public Consulta update(Long id, Consulta cambios, Long turnoId) {
    	normalizarDescripcion(cambios.getDescripcionCorta());
        Consulta existente = consultaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Consulta no encontrada con id " + id));

        Turno turno = turnoRepository.findById(turnoId)
                .orElseThrow(() -> new ResourceNotFoundException("Turno no encontrado con id " + turnoId));

        if (consultaRepository.existsByTurno_IdAndIdNot(turnoId, id)) {
            throw new IllegalArgumentException("El turno ya está asignado a otra consulta");
        }


        existente.setFecha(cambios.getFecha());
        existente.setDuracionMin(cambios.getDuracionMin());
        existente.setComentarios(cambios.getComentarios());
        existente.setTurno(turno);
        existente.setDescripcionCorta(cambios.getDescripcionCorta());

        return consultaRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        if (!consultaRepository.existsById(id)) {
            throw new ResourceNotFoundException("Consulta no encontrada con id " + id);
        }
        consultaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<Consulta> findByFechaBetween(LocalDate desde, LocalDate hasta, Pageable pageable) {
        if (desde == null || hasta == null) throw new IllegalArgumentException("Debe indicar 'desde' y 'hasta'");
        if (desde.isAfter(hasta)) throw new IllegalArgumentException("'desde' no puede ser posterior a 'hasta'");
        return consultaRepository.findByFechaBetween(desde, hasta, pageable);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByPacienteId(Long pacienteId) {
        if (pacienteId == null) throw new IllegalArgumentException("Debe indicar el id del paciente");
        return consultaRepository.countByTurno_Paciente_Id(pacienteId);
    }

	@Override
	public List<Consulta> findDescripcionCorta(String texto) {
		// TODO Auto-generated method stub
		return consultaRepository.findByDescripcionCortaIgnoreCase(texto);
	}
}



