package com.imb2025.smedico.service.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.service.AfiliacionService;
import java.util.List;
import java.util.Optional;

@Service
public class AfiliacionServiceImpl implements AfiliacionService {

	@Autowired
	private AfiliacionRepository afili;

	@Override
	public List<Afiliacion> findAll() {
		return afili.findAll();
	}

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

	@Override
	public void deleteById(Long id) {
		afili.deleteById(id);
	}
}
