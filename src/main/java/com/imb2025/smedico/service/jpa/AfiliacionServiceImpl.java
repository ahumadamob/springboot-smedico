package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.imb2025.dto.mapper.AfiliacionMapper;
import com.imb2025.smedico.dto.request.AfiliacionRequestDto; // package correcto
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IAfiliacionService;

@Service
@Transactional
public class AfiliacionServiceImpl implements IAfiliacionService {

    private final AfiliacionRepository afiliacionRepository;
    private final PacienteRepository pacienteRepository;
    private final ObraSocialRepository obraSocialRepository;

    public AfiliacionServiceImpl(AfiliacionRepository afiliacionRepository,
                                 PacienteRepository pacienteRepository,
                                 ObraSocialRepository obraSocialRepository) {
        this.afiliacionRepository = afiliacionRepository;
        this.pacienteRepository = pacienteRepository;
        this.obraSocialRepository = obraSocialRepository;
    }

    // -------- Lecturas --------
    @Override
    @Transactional(readOnly = true)
    public List<Afiliacion> findAll() {
        return afiliacionRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Afiliacion findById(Long id) {
        return afiliacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Afiliación no encontrada con id " + id));
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsById(Long id) {
        return afiliacionRepository.existsById(id);
    }

    // -------- Escrituras (usan DTO) --------
    @Override
    public Afiliacion create(AfiliacionRequestDto dto) {
        validarFechasDto(dto);

        if (dto.getIdpaciente() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idpaciente es requerido");
        }
        if (dto.getIdobra() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idobra es requerido");
        }

        Paciente paciente = pacienteRepository.findById(dto.getIdpaciente())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado con id " + dto.getIdpaciente()));
        ObraSocial obra = obraSocialRepository.findById(dto.getIdobra())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Obra social no encontrada con id " + dto.getIdobra()));

        Afiliacion afiliacion = AfiliacionMapper.fromDto(dto, paciente, obra);
        return afiliacionRepository.save(afiliacion);
    }

    @Override
    public Afiliacion update(Long id, AfiliacionRequestDto dto) {
        validarFechasDto(dto);

        Afiliacion existente = findById(id); // valida existencia

        if (dto.getIdpaciente() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idpaciente es requerido");
        }
        if (dto.getIdobra() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idobra es requerido");
        }

        Paciente paciente = pacienteRepository.findById(dto.getIdpaciente())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Paciente no encontrado con id " + dto.getIdpaciente()));
        ObraSocial obra = obraSocialRepository.findById(dto.getIdobra())
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Obra social no encontrada con id " + dto.getIdobra()));

        // Usamos el mapper para NO olvidarnos de 'activa'
        AfiliacionMapper.updateFromDto(existente, dto, paciente, obra);

        return afiliacionRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        Afiliacion existente = findById(id); // valida existencia
        afiliacionRepository.delete(existente);
    }

    // -------- Métodos "mágicos" / query methods --------
    @Override
    @Transactional(readOnly = true)
    public List<Afiliacion> findByIdGreaterThan(Long idMin) {
        if (idMin == null || idMin < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idMin debe ser >= 0");
        }
        return afiliacionRepository.findByIdGreaterThan(idMin);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByIdGreaterThan(Long idMin) {
        if (idMin == null || idMin < 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "idMin debe ser >= 0");
        }
        return afiliacionRepository.countByIdGreaterThan(idMin);
    }

    // -------- Ejercicio 1: listados por booleano --------
    @Override
    @Transactional(readOnly = true)
    public List<Afiliacion> findByActivaTrue() {
        return afiliacionRepository.findByActivaTrue();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Afiliacion> findByActivaFalse() {
        return afiliacionRepository.findByActivaFalse();
    }

    // -------- Validaciones privadas --------
    private void validarFechasDto(AfiliacionRequestDto dto) {
        if (dto.getFechaVigenciaDesde() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "fechaVigenciaDesde es requerida");
        }
        if (dto.getFechaHasta() != null &&
            dto.getFechaVigenciaDesde() != null &&
            dto.getFechaHasta().isBefore(dto.getFechaVigenciaDesde())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "La fecha de vigencia hasta no puede ser anterior a la fecha desde");
        }
    }
}
