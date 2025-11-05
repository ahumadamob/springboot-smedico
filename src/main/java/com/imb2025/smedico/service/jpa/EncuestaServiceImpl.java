package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EncuestaRepository;
import com.imb2025.smedico.service.IEncuestaService;

import org.springframework.transaction.annotation.Transactional;

@Service
public class EncuestaServiceImpl implements IEncuestaService {

    @Autowired
    private EncuestaRepository repo;

    @Override
    @Transactional(readOnly = true)
    public List<Encuesta> findAll() { return repo.findAll(); }

    @Override
    @Transactional(readOnly = true)
    public Encuesta findById(Long id) {
        return repo.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Encuesta no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("La Encuesta con ID " + id + " no existe");
        }
        repo.deleteById(id);
    }

    @Override
    public Encuesta create(Encuesta encuesta) {
        return repo.save(encuesta);
    }

    @Override
    public Encuesta update(Long id, Encuesta encuesta)  {
        if (repo.existsById(id)) {
            encuesta.setId(id);
            return repo.save(encuesta);
        } else {
            throw new ResourceNotFoundException("Encuesta con ID " + id + " no existe");
        }
    }

    // ===== TP07 =====
    @Override
    public List<Encuesta> findByPuntajeGreaterThanEqual(int puntajeMin) {
        return repo.findByPuntajeGreaterThanEqual(puntajeMin);
    }

    @Override
    public long countByConsulta(Long consultaId) {
        return repo.countByConsulta_Id(consultaId);
    }
}
