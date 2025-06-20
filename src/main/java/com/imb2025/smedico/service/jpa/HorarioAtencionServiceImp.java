package com.imb2025.smedico.service.jpa; 

import java.util.List;
import java.util.Optional; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.HorarioAtencion;
import com.imb2025.smedico.repository.HorarioAtencionRepository; 
import com.imb2025.smedico.service.IHorarioAtencionService;

@Service
public class HorarioAtencionServiceImp implements IHorarioAtencionService {

	@Autowired
	private HorarioAtencionRepository repo; 

	@Override
	public List<HorarioAtencion> getAllHorarioAtencion() {
		return repo.findAll();
	}

	@Override
	public HorarioAtencion getHorarioAtencionById(Long id) {
		return repo.findById(id).orElse(null);
	}

	@Override
	public HorarioAtencion save(HorarioAtencion horarioAtencionEntity) {
		
		if (horarioAtencionEntity.getId() != null) {
			if (!repo.existsById(horarioAtencionEntity.getId())) {
				
				throw new IllegalArgumentException("Horario de atención con ID " + horarioAtencionEntity.getId() + " no encontrado para actualizar.");
			}
		}
		return repo.save(horarioAtencionEntity);
	}

	
	public void deleteHorarioAtencion(Long id) {
		if (!repo.existsById(id)) {
			
			throw new IllegalArgumentException("Horario de atención con ID " + id + " no encontrado para eliminar.");
		}
		repo.deleteById(id);
	}

}