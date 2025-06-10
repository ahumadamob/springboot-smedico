package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EstudioRequestDTO;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.repository.EspecialidadRepository;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;
import com.imb2025.smedico.service.IEstudioService;

@Service  
public class EstudioServiceImple implements IEstudioService {
	
	@Autowired
	private EstudioRepository repo;
	@Autowired
	private PacienteRepository repoPaciente;
	@Autowired
	private MedicoRepository repoMedico;
	@Autowired
	private EspecialidadRepository repoEspecialidad;
	@Autowired
	private ObraSocialRepository repoObraSocial;
	@Autowired
	private OrdenEstudioRepository repoOredenEstudio;
	@Autowired
	private ResultadoEstudioRepository repoResultadoEstudio;

	@Override
	public List<Estudio> findAll() {
		return repo.findAll();
	}

	@Override
	public Estudio findById(Long id) {
		Optional<Estudio> opt;
		opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}

	@Override
	public Estudio create(EstudioRequestDTO dto) throws Exception{
	    try {
	        Estudio estudio = new Estudio(
	            dto.getNombre(),
	            dto.getDescripcion(),
	            repoPaciente.findById(dto.getPacientId()).orElseThrow(() -> new Exception("Paciente no encontrado")),
	            repoMedico.findById(dto.getMedicoId()).orElseThrow(() -> new Exception("Médico no encontrado")),
	            repoEspecialidad.findById(dto.getEspecialidadId()).orElseThrow(() -> new Exception("Especialidad no encontrada")),
	            repoObraSocial.findById(dto.getObraSocialId()).orElseThrow(() -> new Exception("Obra social no encontrada")),
	            repoOredenEstudio.findById(dto.getOredenEstudioId()).orElseThrow(() -> new Exception("Orden de estudio no encontrada")),
	            repoResultadoEstudio.findById(dto.getResultadoEstudioId()).orElseThrow(() -> new Exception("Resultado de estudio no encontrado"))
	        );

	        return repo.save(estudio);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al crear el estudio: " + e.getMessage(), e);
	    }
	}
	@Override
	public Estudio update(EstudioRequestDTO dto, Long id) {
	    try {
	        if (!repo.existsById(id)) {
	            throw new Exception("No existe el estudio con ID: " + id);
	        }

	        Estudio estudio = new Estudio(
	            dto.getNombre(),
	            dto.getDescripcion(),
	            repoPaciente.findById(dto.getPacientId()).orElseThrow(() -> new Exception("Paciente no encontrado")),
	            repoMedico.findById(dto.getMedicoId()).orElseThrow(() -> new Exception("Médico no encontrado")),
	            repoEspecialidad.findById(dto.getEspecialidadId()).orElseThrow(() -> new Exception("Especialidad no encontrada")),
	            repoObraSocial.findById(dto.getObraSocialId()).orElseThrow(() -> new Exception("Obra social no encontrada")),
	            repoOredenEstudio.findById(dto.getOredenEstudioId()).orElseThrow(() -> new Exception("Orden de estudio no encontrada")),
	            repoResultadoEstudio.findById(dto.getResultadoEstudioId()).orElseThrow(() -> new Exception("Resultado de estudio no encontrado"))
	        );
	        estudio.setId(id);

	        return repo.save(estudio);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al actualizar el estudio: " + e.getMessage(), e);
	    }
	}

	@Override
	public void deleteById(Long id) {
	    if (!repo.existsById(id)) {
	        throw new RuntimeException("No se puede eliminar: estudio con ID " + id + " no existe.");
	    }

	    try {
	        repo.deleteById(id);
	    } catch (Exception e) {
	        throw new RuntimeException("Error al eliminar el estudio: " + e.getMessage(), e);
	    }
	}
}
