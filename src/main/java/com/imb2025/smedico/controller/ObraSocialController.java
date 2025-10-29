package com.imb2025.smedico.controller;

import com.imb2025.smedico.service.IObraSocialService;
import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.ObraSocialRequestDto;
import com.imb2025.smedico.dto.ObraSocialResponseDto;

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
    public ResponseEntity<ApiResponseSuccessDto<List<ObraSocialResponseDto>>> getAll() { 
        List<ObraSocialResponseDto> obras = service.findAll(); 

        ApiResponseSuccessDto<List<ObraSocialResponseDto>> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setData(obras);
        response.setMessage(obras.isEmpty() 
                ? "Sin datos: no hay obras sociales registradas." 
                : "Listado obtenido correctamente.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ObraSocialResponseDto>> getObraSocialById(
            @PathVariable Long id) { 
        ObraSocialResponseDto encontrada = service.findById(id); 

        ApiResponseSuccessDto<ObraSocialResponseDto> resp = new ApiResponseSuccessDto<>();
        resp.setSuccess(true);
        resp.setData(encontrada);
        resp.setMessage("Obra social encontrada correctamente.");
        return ResponseEntity.ok(resp);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<ObraSocialResponseDto>> createObraSocial(
            @Valid @RequestBody ObraSocialRequestDto dto) { 
        ObraSocialResponseDto nueva = service.create(dto); 

        ApiResponseSuccessDto<ObraSocialResponseDto> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setMessage("Obra social creada exitosamente.");
        response.setData(nueva);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ObraSocialResponseDto>> updateObraSocial(
            @PathVariable Long id,
            @Valid @RequestBody ObraSocialRequestDto dto) { 
        ObraSocialResponseDto actualizada = service.update(id, dto); 

        ApiResponseSuccessDto<ObraSocialResponseDto> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setMessage("Obra social actualizada correctamente.");
        response.setData(actualizada);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Void>> delete(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<Void> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setData(null);
        response.setMessage("Obra social eliminada correctamente");
        return ResponseEntity.ok(response);
    }

    // 🔹 NUEVO: endpoint para buscar por nombre (findBy...)
    @GetMapping("/buscar")
    public ResponseEntity<ApiResponseSuccessDto<ObraSocialResponseDto>> getByNombre(
            @RequestParam String nombre) {
        ObraSocialResponseDto encontrada = service.findByNombre(nombre);

        ApiResponseSuccessDto<ObraSocialResponseDto> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setData(encontrada);
        response.setMessage("Obra social encontrada por nombre.");
        return ResponseEntity.ok(response);
    }

    // 🔹 NUEVO: endpoint para contar por cobertura (countBy...)
    @GetMapping("/contar")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByCobertura(
            @RequestParam String cobertura) {
        long cantidad = service.countByCobertura(cobertura);

        ApiResponseSuccessDto<Long> response = new ApiResponseSuccessDto<>();
        response.setSuccess(true);
        response.setData(cantidad);
        response.setMessage("Cantidad de obras sociales con cobertura '" + cobertura + "'.");
        return ResponseEntity.ok(response);
    }
}



