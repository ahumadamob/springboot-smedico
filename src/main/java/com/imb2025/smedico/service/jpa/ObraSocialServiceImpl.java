package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.request.ObraSocialRequestDto;
import com.imb2025.smedico.dto.response.ObraSocialResponseDto;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.mapper.ObraSocialMapper;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.service.IObraSocialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ObraSocialServiceImpl implements IObraSocialService {

    @Autowired
    private ObraSocialRepository repository;

    @Override
    public List<ObraSocialResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(ObraSocialMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    @Override
    public ObraSocialResponseDto findById(Long id) {
        ObraSocial entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ObraSocial no encontrada"));
        return ObraSocialMapper.toResponseDto(entity);
    }

    @Override
    public ObraSocialResponseDto findByNombre(String nombre) {
        ObraSocial entity = repository.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ObraSocial no encontrada"));
        return ObraSocialMapper.toResponseDto(entity);
    }

    @Override
    public ObraSocialResponseDto create(ObraSocialRequestDto dto) {

        if (repository.findByIdentificadorLegibleIgnoreCase(dto.getIdentificadorLegible()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "{ \"errors\": [\"identificadorLegible duplicado\"] }");
        }

        ObraSocial nueva = ObraSocialMapper.fromDto(dto);
        ObraSocial guardada = repository.save(nueva);
        return ObraSocialMapper.toResponseDto(guardada);
    }

    @Override
    public ObraSocialResponseDto update(Long id, ObraSocialRequestDto dto) {
        ObraSocial entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "ObraSocial no encontrada"));

        entity.setIdentificadorLegible(dto.getIdentificadorLegible());
        entity.setNombre(dto.getNombre());
        entity.setTelefono(dto.getTelefono());
        entity.setDireccion(dto.getDireccion());
        entity.setCobertura(dto.getCobertura());
        entity.setFechaVigencia(dto.getFechaVigencia());

        ObraSocial actualizada = repository.save(entity);
        return ObraSocialMapper.toResponseDto(actualizada);
    }
    @Override
    public List<ObraSocialResponseDto> findVigentes() {
        LocalDate hoy = LocalDate.now();
        return repository.findByFechaVigenciaGreaterThanEqual(hoy)
                .stream()
                .map(ObraSocialMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    // 🔹 Obtener registros vencidos (fechaVigencia < hoy)
    @Override
    public List<ObraSocialResponseDto> findVencidos() {
        LocalDate hoy = LocalDate.now();
        return repository.findByFechaVigenciaLessThan(hoy)
                .stream()
                .map(ObraSocialMapper::toResponseDto)
                .collect(Collectors.toList());
    }

    
    @Override
    public void delete(Long id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ObraSocial no encontrada");
        }
        repository.deleteById(id);
    }

	@Override
	public void deleteById(Long id) {
		
	}

	@Override
	public boolean existsById(Long id) {
		return false;
	}

	@Override
	public long countByCobertura(String cobertura) {
		return 0;
	}

	@Override
	public List<ObraSocialResponseDto> findVigentes(LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ObraSocialResponseDto> findVencidos(LocalDate fecha) {
		// TODO Auto-generated method stub
		return null;
	}
}
