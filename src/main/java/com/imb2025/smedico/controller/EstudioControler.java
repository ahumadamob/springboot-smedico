package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.dto.ApiResponseErrorDto;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.EstudioRequestDto;
import com.imb2025.smedico.dto.EstudioResponseDTO;
import com.imb2025.smedico.dto.FieldErrorDto;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.service.IEstudioService;

@RestController
@RequestMapping("/estudio")
public class EstudioControler {

    @Autowired
    private IEstudioService service;

   /* @GetMapping
    public ResponseEntity<List<Estudio>> findAll() {
        List<Estudio> lista = service.findAll();
        if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
    }*/
    
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<Estudio>>> findAll() {
        List<Estudio> lista = service.findAll();

        ApiResponseSuccessDto<List<Estudio>> resp = new ApiResponseSuccessDto<>(
                true,
                lista.isEmpty() ? "No se encontraron estudios" : "Listado de estudios obtenido correctamente",
                lista
        );

        return lista.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(resp);
    }
   

    /*@GetMapping("/{id}")
    public ResponseEntity<Estudio> findById(@PathVariable("id") Long id) {
        Estudio estudio = service.findById(id);
        if (estudio == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(estudio);
    }*/
    
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Estudio>> findById(@PathVariable("id") Long id) {
        Estudio estudio = service.findById(id);

        ApiResponseSuccessDto<Estudio> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio encontrado correctamente",
                estudio
        );

        return ResponseEntity.ok(resp);
    }

    /*@GetMapping("/completo")
    public ResponseEntity<List<EstudioResponseDTO>> findAllDTO() {
        List<EstudioResponseDTO> lista = service.findAll().stream()
                .map(EstudioResponseDTO::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }*/
    
    @GetMapping("/completo")
    public ResponseEntity<ApiResponseSuccessDto<List<EstudioResponseDTO>>> findAllDTO() {
        List<EstudioResponseDTO> lista = service.findAll().stream()
                .map(EstudioResponseDTO::new)
                .collect(Collectors.toList());

        ApiResponseSuccessDto<List<EstudioResponseDTO>> resp = new ApiResponseSuccessDto<>(
                true,
                "Listado de estudios (DTO) obtenido correctamente",
                lista
        );

        return ResponseEntity.ok(resp);
    }

    /*@PostMapping
    public ResponseEntity<Estudio> create(@RequestBody EstudioRequestDto dto) throws Exception {
        Estudio estudio = service.fromDto(dto);
        Estudio creado = service.create(estudio);
        return ResponseEntity.ok(creado);
    }*/
    
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<Estudio>> create(@RequestBody EstudioRequestDto dto) throws Exception {
        Estudio estudio = service.fromDto(dto);
        Estudio creado = service.create(estudio);

        ApiResponseSuccessDto<Estudio> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio creado correctamente",
                creado
        );

        return ResponseEntity.status(201).body(resp);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Estudio> update(@PathVariable("id") Long id, @RequestBody EstudioRequestDto dto) throws Exception {
        Estudio estudio = service.fromDto(dto);
        Estudio actualizado = service.update(id, estudio);
        return ResponseEntity.ok(actualizado);
    }*/
    
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Estudio>> update(@PathVariable("id") Long id, @RequestBody EstudioRequestDto dto) throws Exception {
        Estudio estudio = service.fromDto(dto);
        Estudio actualizado = service.update(id, estudio);

        ApiResponseSuccessDto<Estudio> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio actualizado correctamente",
                actualizado
        );

        return ResponseEntity.ok(resp);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        service.deleteById(id);
        return ResponseEntity.ok("Estudio " + id + " eliminado correctamente.");
    }*/
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable("id") Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<Void> resp = new ApiResponseSuccessDto<>(
                true,
                "Estudio " + id + " eliminado correctamente.",
                null
        );

        return ResponseEntity.ok(resp);
    }

    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body("Error: " + ex.getMessage());
    }*/
    
      
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponseErrorDto> handleNotFound(ResourceNotFoundException ex) {
        ApiResponseErrorDto error = new ApiResponseErrorDto();
        error.setSuccess(false);
        FieldErrorDto fieldError = new FieldErrorDto("id", ex.getMessage());
        error.setErrors(List.of(fieldError));
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(error);
    }
}
