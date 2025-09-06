package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.OrdenEstudioRequestDto;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.service.IOrdenEstudioService;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/ordenestudio")
public class OrdenEstudioController {

    @Autowired
    private IOrdenEstudioService service;

    // GET - Obtener todas las órdenes de estudio
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudio>>> findAllOrdenEstudio() {
        List<OrdenEstudio> ordenes = service.findAll();

        String message;
        if (ordenes.size() == 0) {
            message = "No hay órdenes de estudio disponibles";
        } else {
            message = "Lista de órdenes de estudio";
        }

        ApiResponseSuccessDto<List<OrdenEstudio>> resp =
                new ApiResponseSuccessDto<>(true, message, ordenes);

        return ResponseEntity.ok(resp);
    }

    // GET - Obtener una orden de estudio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudio>> findOrdenEstudioById(@PathVariable("id") Long id) {
        OrdenEstudio orden = service.findById(id);
        ApiResponseSuccessDto<OrdenEstudio> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio encontrada", orden);
        return ResponseEntity.ok(resp);
    }

    // POST - Crear una nueva orden de estudio
    @PostMapping


    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudio>> createOrdenEstudio(@Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {
        OrdenEstudio orden = service.create(service.fromDto(dto));

        ApiResponseSuccessDto<OrdenEstudio> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio creada correctamente", orden);

        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    // PUT - Actualizar una orden de estudio
    @PutMapping("/{id}")

    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudio>> updateOrdenEstudio(@PathVariable("id") Long id,@Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {
        
        OrdenEstudio orden = service.fromDto(dto);
        OrdenEstudio ordenActualizada = service.update(id, orden);


        ApiResponseSuccessDto<OrdenEstudio> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio actualizada correctamente", ordenActualizada);

        return ResponseEntity.ok(resp);
    }

    // DELETE - Eliminar una orden de estudio
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteOrdenEstudio(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio eliminada correctamente", "Id " + id);

        return ResponseEntity.ok(resp);
    }
}
