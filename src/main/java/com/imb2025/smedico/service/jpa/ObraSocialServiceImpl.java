package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IObraSocialService;

import obrasocialrequestdto.ObraSocialRequestDTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repository;

    @Override
    public List<ObraSocial> findAll() {
        return repository.findAll();
    }

    @Override
    public Optional<ObraSocial> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public ObraSocial create(ObraSocial obraSocial) {
        return repository.save(obraSocial);
    }

    @Override
    public ObraSocial update(Long id, ObraSocial obraSocial) throws Exception {
        if (repository.existsById(id)) {
            obraSocial.setId(id);
            return repository.save(obraSocial);
        } else {
            throw new Exception("NO EXISTE LA OBRA SOCIAL SOLICITADA");
        }
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

	@Override
	public ObraSocial guardar(ObraSocialRequestDTO dto) {
		return null;
	}

	@Override
	public ObraSocial fromDto(ObraSocialRequestDTO dto) {
	    ObraSocial obra = new ObraSocial();
	    obra.setNombre(dto.getNombre());
	    obra.setTelefono(dto.getTelefono());
	    obra.setDireccion(dto.getDireccion());
	    obra.setCobertura(dto.getCobertura());
	    return obra;
	}

	@Override
	public ObraSocial save(ObraSocial obraSocial) {
		return null;
	}

}


