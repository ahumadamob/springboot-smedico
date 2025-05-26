package com.imb2025.smedico.service.jpa;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.repository.ConsultorioRepository;
import com.imb2025.smedico.repository.HorarioAtencionRepository;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.repository.TurnoRepository;
import com.imb2025.smedico.service.IHorarioAtencionService;
import com.imb2025.smedicoDtoRequest.HorarioAtencionRequestDTO;
import com.imb2025.smedico.repository.AsistenteRepository;


@Service
public class HorarioAtencionServiceImp implements IHorarioAtencionService {

	@Autowired
	private HorarioAtencionRepository repo;
	@Autowired
	private MedicoRepository repomedico;
	@Autowired
    private TurnoRepository repoturno;
	@Autowired
    private ConsultorioRepository repoconsultorio;
	@Autowired
    private AsistenteRepository repoasistente;

	@Override
	public List<HorarioAtencion> getAllHorarioAtencion() {
		return repo.findAll();
	}


	
	@Override
	public HorarioAtencion getHorarioAtencionById(Long id) {
		Optional<HorarioAtencion> opt;
		opt = repo.findById(id);
		if (opt.isPresent()) {
			return opt.get();
		} else {
			return null;
		}
	}

	@Override
	public HorarioAtencion create ( HorarioAtencionRequestDTO dto) throws Exception {

		HorarioAtencion hora = dtoHorarioAtencion(dto);
		return repo.save(hora);
	}
    
	@Override
    public HorarioAtencion update ( HorarioAtencionRequestDTO dto, Long id) throws Exception {
        Optional<HorarioAtencion> existingHorarioOpt = repo.findById(id);

        if (existingHorarioOpt.isPresent()) {
            HorarioAtencion existingHorario = existingHorarioOpt.get();

            existingHorario.setDiaSemana(dto.getDiaSemana());
            existingHorario.setHoraInicio(dto.getHoraInicio());
            existingHorario.setHoraFin(dto.getHoraFin());

            existingHorario.setMedico(repomedico.findById(dto.getMedicoId())
                                                  .orElseThrow(() -> new Exception("Medico no encontrado")));
            existingHorario.setTurno(repoturno.findById(dto.getTurnoId())
                                                .orElseThrow(() -> new Exception("Turno no encontrado")));
            existingHorario.setConsultorio(repoconsultorio.findById(dto.getConsultorioId())
                                                            .orElseThrow(() -> new Exception("Consultorio no encontrado")));
            existingHorario.setAsistente(repoasistente.findById(dto.getAsistenteId())
                                                      .orElseThrow(() -> new Exception("Asistente no encontrado")));

            return repo.save(existingHorario);
        } else {
            throw new Exception("No existe el horario de atencion");
        }
    }

	@Override
	public void deleteHorarioAtencion(Long id) {
		repo.deleteById(id);
	}
	
	public HorarioAtencion dtoHorarioAtencion(HorarioAtencionRequestDTO dto) {
		 try {
			 HorarioAtencion hora = new HorarioAtencion(
					dto.getDiaSemana(),
					dto.getHoraInicio(),
					dto.getHoraFin(),		            
					repomedico.findById(dto.getMedicoId()).orElseThrow(() -> new Exception("Medico no encontrado")),
					repoturno.findById(dto.getTurnoId()).orElseThrow(() -> new Exception("Turno no encontrado")),
					repoconsultorio.findById(dto.getConsultorioId()).orElseThrow(() -> new Exception("Consultorio no encontrado")),
					repoasistente.findById(dto.getAsistenteId()).orElseThrow(() -> new Exception("Asistente no encontrado"))
		        );

			 return hora;
		    } catch (Exception e) {
		        throw new RuntimeException("Hubo un error: " + e.getMessage(), e);
		    }
	}

}