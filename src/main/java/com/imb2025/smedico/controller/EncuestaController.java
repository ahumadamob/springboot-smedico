package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.service.IEncuestaService;



    @RestController
    @RequestMapping("/api/encuestas")
    public class EncuestaController {

        @Autowired
        private IEncuestaService service;

        @GetMapping
        public ResponseEntity<List<Encuesta>> getAllEncuesta() {
            List <Encuesta> encuesta = service.findAll();

        	return encuesta.isEmpty()
        			? ResponseEntity.noContent().build()
        			: ResponseEntity.ok(encuesta);
        }

        @GetMapping("/{id}")
        public ResponseEntity<ApiResponseSuccessDto<Encuesta>> getEncuestaById(@PathVariable Long id) {
            Encuesta data = service.findById(id);

            ApiResponseSuccessDto<Encuesta> resp =
                new ApiResponseSuccessDto<>(true, "Encuesta encontrada", data);

            return ResponseEntity.ok(resp);
        }



        //Nuevo método POST
        @PostMapping
        public ResponseEntity<Encuesta> createEncuesta(@RequestBody EncuestaRequestDto dto) throws Exception {
        	Encuesta encuesta = service.fromDto(dto);
                return ResponseEntity.ok(service.create(encuesta));

        }

        //Nuevo método PUT
        @PutMapping("/{id}")
        public ResponseEntity<Encuesta> update(@PathVariable Long id, @RequestBody EncuestaRequestDto dto) throws Exception {

        	    Encuesta encuesta = service.fromDto(dto);
                return ResponseEntity.ok(service.update(id, encuesta));


            }



        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }

        

    }
