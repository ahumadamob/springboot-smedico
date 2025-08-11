package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.ConsultaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Turno;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.IConsultaService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ConsultaServiceImpl implements IConsultaService {

    @Autowired
    private ConsultaRepository repository;

    @Autowired
    private TurnoRepository turnoRepository;

    @Override
    public List<Consulta> findAll() {
        return repository.findAll();
    }

    @Override
    public Consulta findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Consulta create(Consulta consulta) {
        return repository.save(consulta);
    }

    @Override
    public Consulta update(Long id, Consulta consulta) {
        consulta.setId(id);
        return repository.save(consulta);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Consulta fromDto(ConsultaRequestDto dto) {
        Turno turno = turnoRepository.findById(dto.getTurnoId()).orElse(null);
        Consulta consulta = new Consulta();
        consulta.setFecha(dto.getFecha());
        consulta.setTurno(turno);
        consulta.setDuracionMin(dto.getDuracionMin());
        consulta.setComentarios(dto.getComentarios());
        return consulta;
    }
}

