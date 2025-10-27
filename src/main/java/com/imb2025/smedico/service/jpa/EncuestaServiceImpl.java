package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.EncuestaRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IEncuestaService;

@Service
@Transactional
public class EncuestaServiceImpl implements IEncuestaService {

    private final EncuestaRepository encuestaRepo;
    private final PacienteRepository pacienteRepo;
    private final ConsultaRepository consultaRepo;

    public EncuestaServiceImpl(
            EncuestaRepository encuestaRepo,
            PacienteRepository pacienteRepo,
            ConsultaRepository consultaRepo
    ) {
        this.encuestaRepo = encuestaRepo;
        this.pacienteRepo = pacienteRepo;
        this.consultaRepo = consultaRepo;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Encuesta> findAll() {
        return encuestaRepo.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Encuesta findById(Long id) {
        return encuestaRepo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Encuesta no encontrada con id " + id));
    }

    @Override
    @Transactional
    public Encuesta create(Encuesta encuesta) {
        return encuestaRepo.save(encuesta);
    }

    @Override
    @Transactional
    public Encuesta update(Long id, Encuesta encuesta) {
        Encuesta db = findById(id);
        db.setPaciente(encuesta.getPaciente());
        db.setConsulta(encuesta.getConsulta());
        db.setPuntaje(encuesta.getPuntaje());
        db.setComentario(encuesta.getComentario());
        return encuestaRepo.save(db);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        if (!encuestaRepo.existsById(id)) {
            throw new ResourceNotFoundException("Encuesta no encontrada con id " + id);
        }
        encuestaRepo.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return encuestaRepo.existsById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Encuesta fromDto(EncuestaRequestDto dto) {
        Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Paciente no encontrado con id " + dto.getPacienteId()));

        Consulta consulta = consultaRepo.findById(dto.getConsultaId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Consulta no encontrada con id " + dto.getConsultaId()));

        Encuesta e = new Encuesta();
        e.setPaciente(paciente);
        e.setConsulta(consulta);
        e.setPuntaje(dto.getPuntaje());
        e.setComentario(dto.getComentario());
        return e;
    }

    // TP07: delegan a “métodos mágicos” del repo
    @Override
    @Transactional(readOnly = true)
    public List<Encuesta> findByPuntajeGreaterThanEqual(int puntajeMin) {
        return encuestaRepo.findByPuntajeGreaterThanEqual(puntajeMin);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByConsulta(Long consultaId) {
        return encuestaRepo.countByConsulta_Id(consultaId);
    }
}
