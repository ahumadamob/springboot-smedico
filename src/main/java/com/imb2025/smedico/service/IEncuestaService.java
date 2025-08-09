package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EncuestaRequestDTO;
import com.imb2025.smedico.entity.Encuesta;


    public interface IEncuestaService {
        public List<Encuesta> findAll();
        public Encuesta findById(Long id);
        public Encuesta create(Encuesta encuesta);
        public Encuesta update(Long id, Encuesta encuesta) throws Exception;
        public Encuesta fromDto(EncuestaRequestDTO dto) throws Exception;
        public void deleteById(Long id);
		public boolean existsById(Long id);



    }