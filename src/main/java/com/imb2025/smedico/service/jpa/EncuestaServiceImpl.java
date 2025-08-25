package com.imb2025.smedico.service.jpa;

import java.util.List;
import com.imb2025.smedico.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Consulta;
import com.imb2025.smedico.entity.Encuesta;
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
        public List<Encuesta> findAll() {
            return repo.findAll();
        }

        @Override
        public Encuesta findById(Long id) {
        	return repo.findById(id)
        		    .orElseThrow(() -> new ResourceNotFoundException(
        		        "Entidad no encontrada con id " + id));
        }

        @Override
		public Encuesta create(Encuesta encuesta) {
        	return repo.save(encuesta);
        }


        @Override
        public Encuesta update(Long id, Encuesta encuesta) throws Exception{
            if(repo.existsById(id)){
                encuesta.setId(id);
                return repo.save(encuesta);
            }else {
                throw new Exception("Encuesta no encontrada");
            }

        }



        @Override
        public Encuesta fromDto(EncuestaRequestDto dto) throws Exception{
            Paciente paciente = pacienteRepo.findById(dto.getPacienteId())
                    .orElseThrow(() -> new Exception("Paciente no encontrado"));
            Consulta consulta = consultaRepo.findById(dto.getConsultaId())
                    .orElseThrow(() -> new Exception("Consulta no encontrada"));

            Encuesta encuesta = new Encuesta();
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