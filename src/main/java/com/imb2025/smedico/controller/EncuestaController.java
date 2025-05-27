package com.imb2025.smedico.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.EncuestaRequestDTO;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.service.IEncuestaService;

import java.util.List;



    @RestController
    @RequestMapping("/api/encuestas")
    public class EncuestaController {

        @Autowired
        private IEncuestaService service;

        @GetMapping
        public List<Encuesta> findAll() {
            return service.findAll();
        }

        @GetMapping("/{id}")
        public Encuesta findById(@PathVariable Long id) {
            return service.findById(id);
        }

        //Nuevo método POST
        @PostMapping
        public Encuesta createEncuesta(@RequestBody EncuestaRequestDTO dto) {
            try {
                return service.create(service.fromDto(dto));
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }

        //Nuevo método PUT
        @PutMapping("/{id}")
        public Encuesta update(@PathVariable Long id, @RequestBody EncuestaRequestDTO dto) {
            try{
                return service.update(id, service.fromDto(dto));
            } catch (Exception e){
                e.printStackTrace();
                return null;
            }
        }


        @DeleteMapping("/{id}")
        public String deleteById(@PathVariable Long id) {
            service.deleteById(id);
            return "Encuesta " + id.toString() + " eliminado correctamente. ";
        }
    }