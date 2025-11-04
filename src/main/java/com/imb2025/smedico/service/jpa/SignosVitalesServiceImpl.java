package com.imb2025.smedico.service.jpa;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.mapper.SignosVitalesMapper;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.SignosVitales;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.SignosVitalesRepository;
import com.imb2025.smedico.service.IConsultaService;
import com.imb2025.smedico.service.ISignosVitalesService;

@Service
public class SignosVitalesServiceImpl implements ISignosVitalesService {

    @Autowired
    private SignosVitalesRepository signos;

    @Autowired
    private IConsultaService consultaService;
    
    SignosVitalesMapper mapper = new SignosVitalesMapper();

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
     public SignosVitales create(SignosVitales dto) {
    	if (signos.findByCodigoRegistroIgnoreCase(dto.getCodigoRegistro()).isPresent()) {
            throw new ResourceNotFoundException("codigoRegistro duplicado");
        }
    	dto.setConsulta(consultaService.findById(dto.getConsulta().getId()));
        return signos.save(dto);
    }

   @Override
public SignosVitales update(Long id, SignosVitales dto) {
       
        SignosVitales existente = signos.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException(
                "No se puede actualizar. Los Signos Vitales con ID " + id + " no existen."));

       
        if (dto.getConsulta() == null || dto.getConsulta().getId() == null) {
            throw new ResourceNotFoundException("Debe incluir un ID de consulta válido para actualizar los signos vitales.");
        }
        Consulta consulta = consultaService.findById(dto.getConsulta().getId());

       
        existente.setFechaHora(dto.getFechaHora());
        existente.setPeso(dto.getPeso());
        existente.setAltura(dto.getAltura());
        existente.setImc(dto.getImc());
        existente.setTemperatura(dto.getTemperatura());
        existente.setFrecuenciaCardiaca(dto.getFrecuenciaCardiaca());
        existente.setPresionSistolica(dto.getPresionSistolica());
        existente.setPresionDiastolica(dto.getPresionDiastolica());
        existente.setSaturacionO2(dto.getSaturacionO2());
        existente.setObservaciones(dto.getObservaciones());
        existente.setFechaVigencia(dto.getFechaVigencia());
        existente.setCodigoRegistro(dto.getCodigoRegistro());
        existente.setConsulta(consulta);

       
        return signos.save(existente);
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
public List<SignosVitales> findByFechas(LocalDate inicio, LocalDate fin) {
	LocalDateTime empieza = inicio.atStartOfDay();
    LocalDateTime termina = fin.atTime(LocalTime.MAX);
    return signos.findByFechaHoraBetween(empieza, termina);
}

@Override
public Long countByConsulta(Long idConsulta) {
	Consulta consulta = consultaService.findById(idConsulta);
    return signos.countByConsulta(consulta);
}

    @Override
    public boolean existsById(Long id) {
        return signos.existsById(id);
    }
    
    @Override
    public List<SignosVitales> findVigentes(LocalDate fecha) {
        return signos.findByFechaVigenciaGreaterThanEqual(fecha);
    }

    @Override
    public List<SignosVitales> findVencidos(LocalDate fecha) {
        return signos.findByFechaVigenciaLessThan(fecha);
    }


}
