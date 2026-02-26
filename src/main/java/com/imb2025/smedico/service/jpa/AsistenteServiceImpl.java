package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.response.AsistenteResponseDto;
import com.imb2025.smedico.entity.Asistente;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.mapper.AsistenteMapper;
import com.imb2025.smedico.repository.AsistenteRepository;
import com.imb2025.smedico.service.IAsistenteService;

/**
 * Implementación de {@link IAsistenteService} usando JPA.
 * Maneja la lógica de negocio para la entidad {@link Asistente}.
 */
@Service
public class AsistenteServiceImpl implements IAsistenteService {

    @Autowired
    private AsistenteRepository repo;

    @Autowired
    private AsistenteMapper mapper;
    
    @Override
    public List<Asistente> findAll() {
        return repo.findAll();
    }

    @Override
    public Asistente findById(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Asistente no encontrado con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public Asistente create(Asistente asistente) {
        return repo.save(asistente);
    }

    @Override
    public Asistente update(Long id, Asistente asistente) {
        Asistente existente = repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "No se puede actualizar. Asistente con id " + id + " no existe."));

        existente.setApellido(asistente.getApellido());
        existente.setNombre(asistente.getNombre());
        existente.setEmail(asistente.getEmail());
        existente.setTelefono(asistente.getTelefono());
        existente.setDni(asistente.getDni());
        existente.setSeveridad(asistente.getSeveridad());

        return repo.save(existente);
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new ResourceNotFoundException(
                    "No se puede eliminar. Asistente con id " + id + " no existe.");
        }
        repo.deleteById(id);
    }
    
 // Métodos mágicos
    @Override
    public List<Asistente> findByApellido(String apellido) {
        return repo.findByApellido(apellido);
    }

    @Override
    public Long countByNombre(String nombre) {
        return repo.countByNombre(nombre);
    }

	@Override
	public List<Asistente> findSeveridadAlta(){
		return repo.findBySeveridadGreaterThanEqual(8);
}

	@Override
	public List<Asistente> findSeveridadBaja() {
		return repo.findBySeveridadLessThanEqual(3);
	}

}
