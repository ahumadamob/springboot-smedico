package com.imb2025.smedico.service;

import java.util.List;

import com.imb2025.smedico.dto.EncuestaRequestDTO;
import com.imb2025.smedico.entity.EncuestaSatisfaccion;


    public interface IEncuestaService {
        public List<EncuestaSatisfaccion> findAll();
        public EncuestaSatisfaccion findById(Long id);
        public EncuestaSatisfaccion create(EncuestaSatisfaccion encuesta);
        public EncuestaSatisfaccion update(Long id, EncuestaSatisfaccion encuesta) throws Exception;
        public EncuestaSatisfaccion fromDto(EncuestaRequestDTO dto) throws Exception;
        public void deleteById(Long id);
                public boolean existsById(Long id);



    }
