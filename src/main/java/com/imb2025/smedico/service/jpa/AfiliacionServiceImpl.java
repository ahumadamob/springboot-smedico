package com.imb2025.smedico.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.service.IAfiliacionService;
<<<<<<< Updated upstream
=======

import jakarta.persistence.EntityNotFoundException;

>>>>>>> Stashed changes
import java.util.List;

@Service
public class AfiliacionServiceImpl implements IAfiliacionService {

	@Autowired
	private AfiliacionRepository afili;

	@Override
	public List<Afiliacion> findAll() {
		return afili.findAll();
	}

<<<<<<< Updated upstream
	@Override
	public Afiliacion findById(Long id) {
		Optional<Afiliacion> opt;
		opt = afili.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}
=======
    @Override
    public Afiliacion findById(Long id) {
        return afili.findById(id).orElseThrow(() ->
            new EntityNotFoundException("Afiliación con ID " + id + " no encontrada.")
        );
    }
>>>>>>> Stashed changes

	@Override
	public Afiliacion save(Afiliacion afiliacion) {
		return afili.save(afiliacion);
	}

	@Override
	public Afiliacion update(Long id, Afiliacion afiliacion) {
		if (afili.existsById(id)) {
			afiliacion.setId(id);
			return afili.save(afiliacion);
		}
		throw new IllegalArgumentException("Afiliación no encontrada");
	}

<<<<<<< Updated upstream
	@Override
	public void deleteById(Long id) {
		afili.deleteById(id);
	}
=======
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
>>>>>>> Stashed changes
}

