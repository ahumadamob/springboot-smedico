package com.imb2025.smedico.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.service.IAfiliacionService;

import java.util.List;
import java.util.Optional;

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
        Optional<Afiliacion> opt = afili.findById(id);
        return opt.orElse(null);
    }

    @Override
    public boolean existsById(Long id) {
        return afili.existsById(id);
    }

    @Override
    public Afiliacion save(Afiliacion afiliacion) {
        return afili.save(afiliacion);
    }

    @Override
    public Afiliacion update(Long id, Afiliacion afiliacion) {
        if (!afili.existsById(id)) {
            throw new IllegalArgumentException("Afiliación con ID " + id + " no encontrada");
        }
        afiliacion.setId(id); // importante para evitar que cree uno nuevo
        return afili.save(afiliacion);
    }

    @Override
    public void deleteById(Long id) {
        if (!afili.existsById(id)) {
            throw new IllegalArgumentException("Afiliación con ID " + id + " no encontrada");
        }
        afili.deleteById(id);
    }
}
