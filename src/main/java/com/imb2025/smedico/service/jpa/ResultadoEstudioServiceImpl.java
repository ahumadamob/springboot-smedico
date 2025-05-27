package com.imb2025.smedico.service.jpa;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.ResultadoEstudioRequestDTO;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;
import com.imb2025.smedico.service.IResultadoEstudioService;

@Service
public  class ResultadoEstudioServiceImpl implements IResultadoEstudioService{

	
	@Autowired
	private ResultadoEstudioRepository repo;
	
	@Autowired
	private OrdenEstudioRepository ordenEstudioRepository;
	
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
	public void deleteById(long id) {
		
		repo.deleteById(id);
	}

	@Override
	public ResultadoEstudio create(ResultadoEstudio resultadoEstudio) {
		System.out.println("ResultadoEstudio Guardado: " + resultadoEstudio);	
		return repo.save(resultadoEstudio);
	}

	@Override
	public ResultadoEstudio update(long id, ResultadoEstudio resultadoEstudio) throws Exception {
		if(repo.existsById(id)) {
			
			resultadoEstudio.setId(id);
			return repo.save(resultadoEstudio);
			
		}else {
			
			throw new Exception("No existe el Resultado del Estudio");
			
		}
		
	}

	@Override
	public ResultadoEstudio fromDto(ResultadoEstudioRequestDTO requestDto) throws Exception {
	    OrdenEstudio ordenEstudio = ordenEstudioRepository.findById(requestDto.getOrdenEstudioId())
	        .orElseThrow(() -> new Exception("Orden de Estudio NO encontrado con ID " + requestDto.getOrdenEstudioId()));

	    return new ResultadoEstudio(
	        ordenEstudio,
	        requestDto.getResultado(),
	        requestDto.getFechaCarga(),
	        requestDto.getObservaciones()
	    );
	}


	
	
	
	
	
	
	
	
	
	
	
	
	
}
