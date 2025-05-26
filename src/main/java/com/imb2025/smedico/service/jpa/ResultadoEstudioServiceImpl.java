package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;
import com.imb2025.smedico.service.IResultadoEstudioService;

@Service
public class ResultadoEstudioServiceImpl implements IResultadoEstudioService{

	
	@Autowired
	private ResultadoEstudioRepository repo;
	
	@Override
	public List<ResultadoEstudio> findAll() {
		
		return repo.findAll();
	}

	@Override
	public ResultadoEstudio findById(long id) {
		Optional<ResultadoEstudio> opt;
		opt = repo.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
		
		
	}

	@Override
	public ResultadoEstudio save(ResultadoEstudio resultadoEstudio) {
		
		return repo.save(resultadoEstudio);
	}

	@Override
	public void deleteById(long id) {
		
		repo.deleteById(id);
	}

}
