package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.ResultadoEstudioRequestDto;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;
import com.imb2025.smedico.service.IResultadoEstudioService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoEstudioServiceImpl implements IResultadoEstudioService {

	
	@Autowired
	private ResultadoEstudioRepository repo;
	
	@Autowired
	private OrdenEstudioRepository ordenEstudioRepository;
	
	@Override
	public List<ResultadoEstudio> findAll() {
		
		return repo.findAll();
	}

	@Override
        public ResultadoEstudio findById(Long id) {

                Optional<ResultadoEstudio> opt = repo.findById(id);
                return opt.orElse(null);


        }

        @Override
        public void deleteById(Long id) {
                if(!repo.existsById(id)) {
                        throw new IllegalArgumentException("No se puede eliminar. No existe un resultadoEstudio con ID: "+ id);
                }
                repo.deleteById(id);

        }
	

	@Override
	public ResultadoEstudio create(ResultadoEstudio resultadoEstudio) {
		System.out.println("ResultadoEstudio Guardado: " + resultadoEstudio);	
		return repo.save(resultadoEstudio);
	}

	@Override
        public ResultadoEstudio update(Long id, ResultadoEstudio resultadoEstudio) throws Exception {
                if (repo.existsById(id)) {
                        resultadoEstudio.setId(id);
                        return repo.save(resultadoEstudio);
                }
                throw new Exception("No existe el Resultado del Estudio con ID: " + id);
        }

	@Override
	public ResultadoEstudio fromDto(ResultadoEstudioRequestDto requestDto) throws Exception {
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
