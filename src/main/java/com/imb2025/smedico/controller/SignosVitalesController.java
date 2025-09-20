package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.SignosVitalesRequestDto;
import com.imb2025.smedico.entity.SignosVitales;
import com.imb2025.smedico.service.ISignosVitalesService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/signosVitales")
public class SignosVitalesController {
	
	@Autowired
    private ISignosVitalesService service;
	
	@GetMapping
    public ResponseEntity<List<SignosVitales>> getAllSignosVitales() {
        List<SignosVitales> lista = service.findAll();

        return lista.isEmpty()
                ? ResponseEntity.noContent().build()
                : ResponseEntity.ok(lista);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<SignosVitales>> getSignosVitalesById(@PathVariable Long id) {
    	SignosVitales data = service.findById(id);

        ApiResponseSuccessDto<SignosVitales> resp =
            new ApiResponseSuccessDto<>(true, "Signos Vitales encontrados", data);

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<SignosVitales>> createSignosVitales(
            @Valid @RequestBody SignosVitalesRequestDto dto) throws Exception {
        
    	SignosVitales servi = service.fromDto(dto);
    	SignosVitales creada = service.create(servi);

        ApiResponseSuccessDto<SignosVitales> resp =
            new ApiResponseSuccessDto<>(true, "Signos Vitales creados con éxito", creada);

        return ResponseEntity.status(201).body(resp);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<SignosVitales>> updateSignosVitales(
            @PathVariable Long id, 
            @Valid @RequestBody SignosVitalesRequestDto dto) throws Exception {
        
    	SignosVitales signos = service.fromDto(dto);
    	SignosVitales actualizada = service.update(id, signos);

        ApiResponseSuccessDto<SignosVitales> resp =
            new ApiResponseSuccessDto<>(true, "Signos Vitales actualizados con éxito", actualizada);

        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSignosVitales(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
