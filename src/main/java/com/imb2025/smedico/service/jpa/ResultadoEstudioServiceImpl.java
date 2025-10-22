package com.imb2025.smedico.service.jpa;



import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.EstudioRepository;
import com.imb2025.smedico.repository.OrdenEstudioRepository;
import com.imb2025.smedico.repository.ResultadoEstudioRepository;
import com.imb2025.smedico.service.IResultadoEstudioService;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResultadoEstudioServiceImpl implements IResultadoEstudioService {

	
	@Autowired
	private ResultadoEstudioRepository repo;
	
	@Autowired
	private OrdenEstudioRepository ordenEstudioRepository;
	
	@Autowired
	private EstudioRepository estudioRepository;
	
	@Override
	public List<ResultadoEstudio> findAll() {
		
		return repo.findAll();
	}

	@Override
        public ResultadoEstudio findById(Long id) {

		return repo.findById(id)
			    .orElseThrow(() -> new ResourceNotFoundException(
			        "Entidad no encontrada con id " + id));

        }

        @Override
        public boolean existsById(Long id) {
                return repo.existsById(id);
        }

        @Override
        public void deleteById(Long id) {
                if(!repo.existsById(id)) {
                        throw new ResourceNotFoundException("No se puede eliminar. No existe un resultadoEstudio con ID: "+ id);
                }
                repo.deleteById(id);

        }
	

	@Override
	public ResultadoEstudio create(ResultadoEstudio resultadoEstudio) {	
		return repo.save(resultadoEstudio);
	}

	@Override
        public ResultadoEstudio update(Long id, ResultadoEstudio resultadoEstudio) {
                if (repo.existsById(id)) {
                        resultadoEstudio.setId(id);
                        return repo.save(resultadoEstudio);
                }
                throw new ResourceNotFoundException("No existe el Resultado del Estudio con ID: " + id);
        }

	

	@Override
	public List<ResultadoEstudio> findByFechaCarga(LocalDate fechaCarga) {

		return repo.findByFechaCarga(fechaCarga);
	}

	@Override
	public long countByFechaCarga(LocalDate fechaCarga) {
		
		return repo.countByFechaCarga(fechaCarga);
	}
	
	public List<ResultadoEstudio> listarTrue(){
		return repo.findByAtributoBooleanoTrue();
	}
	
	public List<ResultadoEstudio> listarFalse(){
		return repo.findByAtributoBooleanoFalse();
	}
	
}
