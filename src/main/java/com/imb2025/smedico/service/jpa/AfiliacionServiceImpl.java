package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.service.IAfiliacionService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AfiliacionServiceImpl implements IAfiliacionService {

    @Autowired
    private AfiliacionRepository afili;

    @Override
    public List<Afiliacion> findAll() {
        return afili.findAll();
    }

    @Override
    public Afiliacion findById(Long id) {
        return afili.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Afiliación con ID " + id + " no encontrada."));
    }

    @Override
    public boolean existsById(Long id) {
        return afili.existsById(id);
    }

    @Override
    public Afiliacion create(Afiliacion afiliacion) {
        return afili.save(afiliacion);
    }

    @Override
    public Afiliacion update(Long id, Afiliacion afiliacion) {
        if (!afili.existsById(id)) {
            throw new EntityNotFoundException("No se puede actualizar. Afiliación con ID " + id + " no existe.");
        }
        afiliacion.setId(id);
        return afili.save(afiliacion);
    }

    @Override
    public void deleteById(Long id) {
        if (!afili.existsById(id)) {
            throw new EntityNotFoundException("No se puede eliminar. Afiliación con ID " + id + " no existe.");
        }
        afili.deleteById(id);
    }
}

