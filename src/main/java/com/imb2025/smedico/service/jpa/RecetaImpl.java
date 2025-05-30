package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.RecetaRequestDTO;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.entity.Receta;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.RecetaRepository;
import com.imb2025.smedico.service.IRecetaService;
@Service
public class RecetaImpl implements IRecetaService{
	
	@Autowired
	private RecetaRepository repo;
	
	@Autowired
	private MedicoRepository medicoRepository;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Override
	public List<Receta> findAll() {
		return repo.findAll();
	}

	@Override
	public Receta findById(Long id) {
		Optional<Receta> opt;
        opt = repo.findById(id);
        if(opt.isPresent()) {
            return opt.get();
        }else {
            return null;
        }
	}

	@Override
	public Receta create(RecetaRequestDTO dto) {
	    try {
	        Receta receta = fromDto(dto);
	        return repo.save(receta);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al crear la receta: " + e.getMessage(), e);
	    }
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	@Override
	public Receta update(Long id, RecetaRequestDTO dto) throws Exception {
	    Receta recetaExistente = repo.findById(id)
	        .orElseThrow(() -> new Exception("Receta con ID " + id + " no encontrada"));
	    Medico medico = medicoRepository.findById(dto.getMedicoId())
	        .orElseThrow(() -> new Exception("Medico con ID " + dto.getMedicoId() + " no encontrado"));
	    Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
	        .orElseThrow(() -> new Exception("Paciente con ID " + dto.getPacienteId() + " no encontrado"));
	    recetaExistente.setFecha(dto.getFecha());
	    recetaExistente.setObservaciones(dto.getObservaciones());
	    recetaExistente.setMedico(medico);
	    recetaExistente.setPaciente(paciente);

	    return repo.save(recetaExistente);
	}
	 
    public Receta fromDto(RecetaRequestDTO dto)throws Exception {
    	Medico medico = medicoRepository.findById(dto.getMedicoId()).orElseThrow(() -> new Exception("Medico no encontrado"));
    	Paciente paciente = pacienteRepository.findById(dto.getPacienteId()).orElseThrow(() -> new Exception("Paciente no encontrado"));
    	Receta receta = new Receta (dto.getFecha(), dto.getObservaciones(), medico, paciente);
    	return receta;
    	
}
	
	

}
