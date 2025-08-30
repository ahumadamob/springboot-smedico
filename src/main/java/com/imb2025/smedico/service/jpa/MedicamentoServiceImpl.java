package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.MedicamentoRequestDto;
import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.MedicamentoRepository;
import com.imb2025.smedico.service.IMedicamentoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class MedicamentoServiceImpl implements IMedicamentoService {
	
	@Autowired
	private MedicamentoRepository repoMedic;
	
	@Override
        public List<Medicamento> findAll() {
                return repoMedic.findAll();
        }

        public Medicamento findById(Long id) {
        	return repoMedic.findById(id).orElseThrow(() -> new ResourceNotFoundException("Entidad no encontrada con id " + id));
        }
	
	@Override
        public Medicamento create(Medicamento medicamento) throws Exception {
                return repoMedic.save(medicamento);
        }
	
	
	@Override
        public Medicamento update(Long id, Medicamento medicamento) throws Exception {
            if (!repoMedic.existsById(id)) {
                throw new Exception("El medicamento con ID " + id + " no existe.");
            }

            Medicamento medExistente = repoMedic.findById(id).get();
            medExistente.setNombre(medicamento.getNombre());
            medExistente.setDosisSugerida(medicamento.getDosisSugerida());
            medExistente.setPresentacion(medicamento.getPresentacion());
            return repoMedic.save(medExistente);
        }
	
	@Override
	public boolean existsById(Long id) {
		return repoMedic.existsById(id);
	}
	
	
	@Override
	public void deleteById(Long id) {
		if(!repoMedic.existsById(id)) {
			throw new IllegalArgumentException("El Medicamento con ID: "+ id +" no existe.");
		}
		repoMedic.deleteById(id);		
	}
	
	@Override
	public Medicamento fromDto(MedicamentoRequestDto dto) throws Exception {
		if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar nulo o vacío");
        }
		if (dto.getDosisSugerida() == null || dto.getDosisSugerida().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar nulo o vacío");
        }
		if (dto.getPresentacion() == null || dto.getPresentacion().isBlank()) {
            throw new IllegalArgumentException("El nombre no puede estar nulo o vacío");
        }
		
		Medicamento medicamento = new Medicamento();
		
		medicamento.setNombre(dto.getNombre());
		medicamento.setDosisSugerida(dto.getDosisSugerida());
		medicamento.setPresentacion(dto.getPresentacion());
	    
		return medicamento;
	}
	

}
