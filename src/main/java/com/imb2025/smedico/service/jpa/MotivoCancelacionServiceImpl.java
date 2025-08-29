package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.MotivoCancelacionRequestDto;
import com.imb2025.smedico.entity.MotivoCancelacion;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.MotivoCancelacionRepository;
import com.imb2025.smedico.service.IMotivoCancelacionService;

@Service
public class MotivoCancelacionServiceImpl implements IMotivoCancelacionService {

        @Autowired
        private MotivoCancelacionRepository repo;

	@Override
        public List<MotivoCancelacion> findAll() {
                return repo.findAll();
        }

	@Override
    public MotivoCancelacion findById(Long id) {
        return repo.findById(id)
        	    .orElseThrow(() -> new ResourceNotFoundException(
        	        "Entidad no encontrada con id " + id));
    }

    @Override
    public boolean existsById(Long id) {
        return repo.existsById(id);
    }

    @Override
    public MotivoCancelacion create(MotivoCancelacion motivoCancelacion) {
        return repo.save(motivoCancelacion);
    }

    @Override
    public MotivoCancelacion update(Long id, MotivoCancelacion motivoCancelacion) throws Exception {
        if (repo.existsById(id)) {
            motivoCancelacion.setId(id);
            return repo.save(motivoCancelacion);
        }
        throw new Exception("MotivoCancelacion con ID " + id + " no existe");
    }

    @Override
    public void deleteById(Long id) {
        if (!repo.existsById(id)) {
            throw new IllegalArgumentException("El MotivoCancelacion con ID " + id + " no existe");
        }
        repo.deleteById(id);
    }

    @Override
    public MotivoCancelacion fromDto(MotivoCancelacionRequestDto dto) {
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar nulo o vacío");
        }
        if (dto.getDescripcion() == null || dto.getDescripcion().isBlank()) {
            throw new IllegalArgumentException("La descripción no puede estar nula o vacía");
        }
        MotivoCancelacion motivoCancelacion = new MotivoCancelacion();
        motivoCancelacion.setNombre(dto.getNombre());
        motivoCancelacion.setDescripcion(dto.getDescripcion());
        return motivoCancelacion;
    }
}
