package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IObraSocialService;
@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repository;

    @Override
    public List<ObraSocial> findAll() {
        return repository.findAll();
    }

    @Override
    public ObraSocial findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public ObraSocial create(ObraSocial obraSocial) throws Exception {
        return repository.save(obraSocial);
    }

    @Override
    public ObraSocial update(Long id, ObraSocial obraSocial) throws Exception {
        if (repository.existsById(id)) {
            obraSocial.setId(id);
            return repository.save(obraSocial);
        }
        throw new Exception("NO EXISTE LA OBRA SOCIAL SOLICITADA");
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public ObraSocial fromDto(ObraSocialRequestDto dto) throws Exception {
        ObraSocial obraSocial = new ObraSocial();
        obraSocial.setNombre(dto.getNombre());
        obraSocial.setTelefono(dto.getTelefono());
        obraSocial.setDireccion(dto.getDireccion());
        obraSocial.setCobertura(dto.getCobertura());
        return obraSocial;
    }

    @Override
    public boolean existsById(Long id) {
        return repository.existsById(id);
    }

}


