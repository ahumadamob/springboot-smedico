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
    public SignosVitales create(SignosVitales signosVitales) {
        return signos.save(signosVitales);
    }

   @Override
public SignosVitales update(Long id, SignosVitales signosVitales) {
    if (!signos.existsById(id)) {
        throw new ResourceNotFoundException(
                "No se puede actualizar. Los Signos Vitales con ID " + id + " no existen.");
    }
    signosVitales.setId(id);
    return signos.save(signosVitales);
}

@Override
public void deleteById(Long id) {
    if (!signos.existsById(id)) {
        throw new ResourceNotFoundException(
                "No se puede eliminar. Los Signos Vitales con ID " + id + " no existen.");
    }
    signos.deleteById(id);
}


    @Override
    public SignosVitales fromDto(SignosVitalesRequestDto dto) {
        SignosVitales s = new SignosVitales();
        s.setFechaHora(dto.getFechaHora());
        s.setPeso(dto.getPeso());
        s.setAltura(dto.getAltura());
        s.setImc(dto.getImc());
        s.setTemperatura(dto.getTemperatura());
        s.setFrecuenciaCardiaca(dto.getFrecuenciaCardiaca());
        s.setPresionSistolica(dto.getPresionSistolica());
        s.setPresionDiastolica(dto.getPresionDiastolica());
        s.setSaturacionO2(dto.getSaturacionO2());
        s.setObservaciones(dto.getObservaciones());
        if (dto.getIdConsulta() != null) {
            s.setConsulta(consultaService.findById(dto.getIdConsulta()));
        }
        return s;
    }

    @Override
    public boolean existsById(Long id) {
        return signos.existsById(id);
    }

    @Override
    public SignosVitalesRequestDto toDto(SignosVitales s) {
        if (s == null) return null;
        Long idConsulta = s.getConsulta() != null ? s.getConsulta().getId() : null;
        return new SignosVitalesRequestDto(
                s.getFechaHora(),
                s.getPeso(),
                s.getAltura(),
                s.getImc(),
                s.getTemperatura(),
                s.getFrecuenciaCardiaca(),
                s.getPresionSistolica(),
                s.getPresionDiastolica(),
                s.getSaturacionO2(),
                s.getObservaciones(),
                idConsulta
        );
    }
}
