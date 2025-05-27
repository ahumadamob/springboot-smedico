package com.imb2025.smedico.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.DTO.AfiliacionRequestDTO;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.repository.AfiliacionRepository;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IAfiliacionService;
import java.util.List;
import java.util.Optional;

@Service
public class AfiliacionServiceImpl implements IAfiliacionService {

	@Autowired
	private AfiliacionRepository repo;
	@Autowired
	private PacienteRepository repopaciente;
	@Autowired
	private ObraSocialRepository repoobra;

	@Override
	public List<Afiliacion> findAll() {
		return repo.findAll();
	}

	@Override
	public Afiliacion findById(Long id) {
		Optional<Afiliacion> opt;
		opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public Afiliacion create(AfiliacionRequestDTO dto) throws Exception {
		Afiliacion afiliacion = dtoAfiliacion(dto);
		return repo.save(afiliacion);
	}

	@Override
	public Afiliacion update(Long id, AfiliacionRequestDTO dto) throws Exception {
		Afiliacion afiliacion = dtoAfiliacion(dto);
		if (repo.existsById(id)) {
			afiliacion.setId(id);
			return repo.save(afiliacion);
		}
		throw new Exception("Afiliación no encontrada");
	}

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

	public Afiliacion dtoAfiliacion(AfiliacionRequestDTO dto) {
		 try {
			 Afiliacion fili = new Afiliacion(
					dto.getNumeroAfiliado(),
					dto.getFechaVigenciaDesde(),
					dto.getFechaHasta(),		            
					repopaciente.findById(dto.getIdpaciente()).orElseThrow(() -> new Exception("Paciente no encontrado")),
					repoobra.findById(dto.getIdobra()).orElseThrow(() -> new Exception("Obra social no encontrada"))					
		        );

			 return fili;
		    } catch (Exception e) {
		    	System.err.println("Error al convertir el DTO a Afiliacion: " + e.getMessage());
		    	throw new RuntimeException("Hubo un error: " + e.getMessage(), e);
				}
			}

}
