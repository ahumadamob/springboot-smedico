package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.SignosVitalesRequestDto;
import com.imb2025.smedico.entity.SignosVitales;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.SignosVitalesRepository;
import com.imb2025.smedico.service.IConsultaService;
import com.imb2025.smedico.service.ISignosVitalesService;

import jakarta.persistence.EntityNotFoundException;

@Service
public class SignosVitalesServiceImpl implements ISignosVitalesService {

	@Autowired
	private SignosVitalesRepository signos;

	@Autowired
	private IConsultaService consultaService;

	@Override
	public List<SignosVitales> findAll() {
		return signos.findAll();
	}

	@Override
	public SignosVitales findById(Long id) {
		return signos.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Signos Vitales no encontrados con id " + id));
	}

	@Override
	public SignosVitales create(SignosVitales signosVitales) throws Exception {
		return signos.save(signosVitales);
	}

	@Override
	public SignosVitales update(Long id, SignosVitales signosVitales) throws Exception {
		if (!signos.existsById(id)) {
			throw new EntityNotFoundException(
					"No se puede actualizar. Los Signos Vitales con ID " + id + " no existen.");
		}
		signosVitales.setId(id);
		return signos.save(signosVitales);
	}

	@Override
	public void deleteById(Long id) {
		if (!signos.existsById(id)) {
			throw new EntityNotFoundException("No se puede eliminar. Los Signos Vitales con ID " + id + " no existen.");
		}
		signos.deleteById(id);
	}

	@Override
	public SignosVitales fromDto(SignosVitalesRequestDto dto) throws Exception {
		SignosVitales signosVitales = new SignosVitales();
		signosVitales.setFecha_hora(dto.getFecha_hora());
		signosVitales.setPeso(dto.getPeso());
		signosVitales.setAltura(dto.getAltura());
		signosVitales.setImc(dto.getImc());
		signosVitales.setTemperatura(dto.getTemperatura());
		signosVitales.setFrecuencia_cardiaca(dto.getFrecuencia_cardiaca());
		signosVitales.setPresion_sistolica(dto.getPresion_sistolica());
		signosVitales.setPresion_diastolica(dto.getPresion_diastolica());
		signosVitales.setSaturacion_o2(dto.getSaturacion_o2());
		signosVitales.setObservaciones(dto.getObservaciones());
		if (dto.getIdConsulta() != null) {
			signosVitales.setConsulta(consultaService.findById(dto.getIdConsulta()));
		}

		return signosVitales;
	}

	@Override
	public boolean existsById(Long id) {
		return signos.existsById(id);
	}

}