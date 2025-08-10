package com.imb2025.smedico.service.jpa;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EncuestaRequestDTO;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.EncuestaSatisfaccion;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.ConsultaRepository;
import com.imb2025.smedico.repository.EncuestaRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IEncuestaService;

    @Service
    public class EncuestaServiceImpl implements IEncuestaService {

        @Autowired
        private EncuestaRepository repo;
        @Autowired
        private PacienteRepository pacienteRepo;
        @Autowired
        private ConsultaRepository consultaRepo;

        @Override
        public List<EncuestaSatisfaccion> findAll() {
            return repo.findAll();
        }

        @Override
        public EncuestaSatisfaccion findById(Long id) {
            return repo.findById(id).orElseThrow(() -> new RuntimeException("Encuesta no encontrada") ) ;
        }
        
        public EncuestaSatisfaccion create(EncuestaSatisfaccion encuesta) {
                return repo.save(encuesta);
        }
       

        @Override
        public EncuestaSatisfaccion update(Long id, EncuestaSatisfaccion encuesta) throws Exception{
            if(repo.existsById(id)){
                encuesta.setId(id);
                return repo.save(encuesta);
            }else {
                throw new Exception("Encuesta no encontrada");
            }

        }

       

        @Override
        public EncuestaSatisfaccion fromDto(EncuestaRequestDTO dto) throws Exception{
            Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
                    .orElseThrow(() -> new Exception("Paciente no encontrado"));
            Consulta consulta = consultaRepo.findById(dto.getConsultaId())
                    .orElseThrow(() -> new Exception("Consulta no encontrada"));
            
            EncuestaSatisfaccion encuesta = new EncuestaSatisfaccion();
            encuesta.setPaciente(paciente);
            encuesta.setConsulta(consulta);
            encuesta.setPuntaje(dto.getPuntaje());
            encuesta.setComentario(dto.getComentario());

            return encuesta;
        }		

		

		 @Override
	        public void deleteById(Long id) {
	            if (repo.existsById(id)) {
	            	repo.deleteById(id);
	            } else {
	            	throw new RuntimeException("Encuesta no encontrada");
	            }
	        	
	        }

		@Override
		public boolean existsById(Long id) {
			
			return repo.existsById(id);
		}


    }