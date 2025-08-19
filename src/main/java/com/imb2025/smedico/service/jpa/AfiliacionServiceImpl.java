package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.AfiliacionRequestDto;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;
import com.imb2025.smedico.service.IObraSocialService;
import com.imb2025.smedico.service.IPacienteService;
import com.imb2025.smedico.repository.AfiliacionRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class AfiliacionServiceImpl implements IAfiliacionService {

    @Autowired
    private AfiliacionRepository afili;

    @Autowired
    private IPacienteService pacienteService;

    @Autowired
    private IObraSocialService obraSocialService;

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
    public Afiliacion create(Afiliacion afiliacion) throws Exception {
        return afili.save(afiliacion);
    }

    @Override
    public Afiliacion update(Long id, Afiliacion afiliacion) throws Exception {
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

    @Override
    public Afiliacion fromDto(AfiliacionRequestDto dto) throws Exception {
        Afiliacion afiliacion = new Afiliacion();
        afiliacion.setNumeroAfiliado(dto.getNumeroAfiliado());
        afiliacion.setFechaVigenciaDesde(dto.getFechaVigenciaDesde());
        afiliacion.setFechaHasta(dto.getFechaHasta());
        if (dto.getIdpaciente() != null) {
            afiliacion.setPaciente(pacienteService.findById(dto.getIdpaciente()));
        }
        if (dto.getIdobra() != null) {
            afiliacion.setObra(obraSocialService.findById(dto.getIdobra()));
        }
        return afiliacion;
    }

    @Override
    public boolean existsById(Long id) {
        return afili.existsById(id);
    }
}

