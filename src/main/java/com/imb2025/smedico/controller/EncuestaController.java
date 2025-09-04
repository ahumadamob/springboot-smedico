package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.EncuestaRequestDto;
import com.imb2025.smedico.entity.Encuesta;
import com.imb2025.smedico.service.IEncuestaService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/encuestas")
public class EncuestaController {

    @Autowired
    private IEncuestaService service;


    @GetMapping
    public ResponseEntity<List<Encuesta>> getAllEncuesta() {
        List<Encuesta> encuesta = service.findAll();
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

    // POST con @Valid
    @PostMapping
    public ResponseEntity<Encuesta> createEncuesta(@Valid @RequestBody EncuestaRequestDto dto) throws Exception {
        Encuesta encuesta = service.fromDto(dto);
        Encuesta creada = service.create(encuesta);
        return ResponseEntity.status(HttpStatus.CREATED).body(creada);
    }

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



    // PUT con @Valid
    @PutMapping("/{id}")
    public ResponseEntity<Encuesta> update(@PathVariable Long id,
                                           @Valid @RequestBody EncuestaRequestDto dto) throws Exception {
        Encuesta encuesta = service.fromDto(dto);
        return ResponseEntity.ok(service.update(id, encuesta));
    }

 tp05-jordan-humberto
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteById(@PathVariable Long id) {
            service.deleteById(id);
            return ResponseEntity.ok().build();
        }

        

    }

