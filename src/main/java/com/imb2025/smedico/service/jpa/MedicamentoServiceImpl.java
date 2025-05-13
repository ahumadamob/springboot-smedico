package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.Medicamento;
import com.imb2025.smedico.repository.MedicamentoRepository;
import com.imb2025.smedico.service.IMedicamentoService;

@Service
public class MedicamentoServiceImpl implements IMedicamentoService{
	
	@Autowired
	private MedicamentoRepository repoMedic;
	
	@Override
	public List<Medicamento> findAll() {
		return repoMedic.findAll();
	}

	public Medicamento findById(Long id) {
		Optional<Medicamento> opt;
		opt = repoMedic.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}
	
	@Override
	public Medicamento save(Medicamento medicamento) {
		return repoMedic.save(medicamento);
	}

	@Override
	public void deleteById(Long id) {
		repoMedic.deleteById(id);		
	}
	

}
