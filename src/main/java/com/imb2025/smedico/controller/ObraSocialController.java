package com.imb2025.smedico.controller;

import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.service.IObraSocialService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.ApiResponseErrorDto;
import com.imb2025.smedico.dto.ObraSocialRequestDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/obrasocial")
public class ObraSocialController {

    @Autowired
    private IObraSocialService service;

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<ObraSocial>>> getAll() {
        List<ObraSocial> obras = service.findAll();
        ApiResponseSuccessDto<List<ObraSocial>> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setData(obras);
        response.setMessage(obras.isEmpty() ? "Sin datos: no hay obras sociales registradas." : "Listado de obras sociales obtenido correctamente.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getObraSocialById(@PathVariable Long id) {
        ObraSocial encontrada = service.findById(id);
        if (encontrada == null) {
            ApiResponseErrorDto error = new ApiResponseErrorDto("No se encontró una obra social con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        ApiResponseSuccessDto<ObraSocial> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(encontrada);
        resp.setMessage("Obra social encontrada correctamente.");

        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<ObraSocial>> createObraSocial(
            @Valid @RequestBody ObraSocialRequestDto dto) throws Exception {
        
        ObraSocial nueva = service.create(service.fromDto(dto));
        
        ApiResponseSuccessDto<ObraSocial> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setMessage("Obra social creada exitosamente.");
        response.setData(nueva);

        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


   

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizarObraSocial(@Valid @PathVariable Long id, @RequestBody ObraSocialRequestDto dto) throws Exception {
        ObraSocial obraSocial = service.findById(id);
        if (obraSocial == null) {
            ApiResponseErrorDto error = new ApiResponseErrorDto("No se encontró una obra social con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        obraSocial.setNombre(dto.getNombre());
        obraSocial.setTelefono(dto.getTelefono());
        obraSocial.setDireccion(dto.getDireccion());
        obraSocial.setCobertura(dto.getCobertura());

        service.update(id, obraSocial);

        ApiResponseSuccessDto<ObraSocial> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setData(obraSocial);
        response.setMessage("Datos actualizados correctamente");

        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        ObraSocial obra = service.findById(id);
        if (obra == null) {
            ApiResponseErrorDto error = new ApiResponseErrorDto("No se encontró una obra social con el ID: " + id);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }

        service.deleteById(id);

        ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setData(null);
        response.setMessage("Obra social eliminada correctamente");

        return ResponseEntity.ok(response);
    }
}


