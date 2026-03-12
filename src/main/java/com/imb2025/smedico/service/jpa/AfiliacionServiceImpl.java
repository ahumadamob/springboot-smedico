package com.imb2025.smedico.service.jpa;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.service.IAfiliacionService;

@Service
@Transactional
public class AfiliacionServiceImpl implements IAfiliacionService {

    private final AfiliacionRepository afiliacionRepository;

    public AfiliacionServiceImpl(AfiliacionRepository afiliacionRepository) {
        this.afiliacionRepository = afiliacionRepository;
    }

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

    @Override
    public Afiliacion create(Afiliacion afiliacion) {
        return afiliacionRepository.save(afiliacion);
    }

    @Override
    public Afiliacion update(Long id, Afiliacion afiliacion) {
        Afiliacion existente = findById(id);
        
        existente.setNumeroAfiliado(afiliacion.getNumeroAfiliado());
        existente.setFechaVigenciaDesde(afiliacion.getFechaVigenciaDesde());
        existente.setFechaHasta(afiliacion.getFechaHasta());
        existente.setPaciente(afiliacion.getPaciente());
        existente.setObra(afiliacion.getObra());

        return afiliacionRepository.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        Afiliacion existente = findById(id);
        afiliacionRepository.delete(existente);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Afiliacion> findByIdGreaterThan(Long idMin) {
        return afiliacionRepository.findByIdGreaterThan(idMin);
    }

    @Override
    @Transactional(readOnly = true)
    public long countByIdGreaterThan(Long idMin) {
        return afiliacionRepository.countByIdGreaterThan(idMin);
    }
}