package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.mapper.MedicamentoMapper;
import com.imb2025.smedico.repository.MedicamentoRepository;
import com.imb2025.smedico.service.IMedicamentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MedicamentoServiceImpl implements IMedicamentoService {
	
	@Autowired
	private MedicamentoRepository repoMedic;
	
	@Autowired
    private MedicamentoMapper mapper;
	
	@Override
        public List<Medicamento> findAll() {
                return repoMedic.findAll();
        }

        public Medicamento findById(Long id) {
        	return repoMedic.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entidad no encontrada con id " + id));
        }
	
	@Override
	public Medicamento create(MedicamentoRequestDto dto) {
        Medicamento medicamento = mapper.toEntity(dto); // Usamos el mapper
        return repoMedic.save(medicamento);
        }
	
	
	@Override
	public Medicamento update(Long id, MedicamentoRequestDto dto) {
		Medicamento medExistente = repoMedic.findById(id)
	        .orElseThrow(() -> new ResourceNotFoundException("Medicamento con ID " + id + " no existe."));
		    
        // Usamos el mapper para actualizar
        mapper.updateEntityFromDto(dto, medExistente); 
		    
		return repoMedic.save(medExistente);	
    }
	
	
	@Override
	public boolean existsById(Long id) {
		return repoMedic.existsById(id);
	}
	
	
	@Override
	public void deleteById(Long id) {
		Medicamento medicamento = repoMedic.findById(id)
		        .orElseThrow(() -> new ResourceNotFoundException("El Medicamento con ID " + id + " no existe."));

		    repoMedic.delete(medicamento);		
	}
	
	
	@Override
    public List<Medicamento> findActivos() {
        return repoMedic.findByEsActivoTrue();
    }

    @Override
    public List<Medicamento> findInactivos() {
        return repoMedic.findByEsActivoFalse();
    }
	

}
