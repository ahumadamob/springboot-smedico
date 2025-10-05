package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.service.IMotivoCancelacionService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.MotivoCancelacionRequestDto;
import com.imb2025.smedico.entity.MotivoCancelacion;

@RestController
public class MotivoCancelacionController {
	
	@Autowired
	private IMotivoCancelacionService service;
	
	@GetMapping("/motivocancelacion")
    public ResponseEntity<ApiResponseSuccessDto<List<MotivoCancelacion>>> findAllMotivoCancelacion() {
        List<MotivoCancelacion> lista = service.findAll();
        ApiResponseSuccessDto<List<MotivoCancelacion>> resp = new ApiResponseSuccessDto<>(true,lista.isEmpty() ? "No hay motivos de cancelación disponibles" : "Lista de motivos de cancelación",lista);
        return ResponseEntity.ok(resp);
    }

    @GetMapping("/motivocancelacion/{id}")
    public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> findMotivoCancelacionById(@PathVariable("id") Long id) {
        MotivoCancelacion motivoCancelacion = service.findById(id);
        ApiResponseSuccessDto<MotivoCancelacion> resp =
                new ApiResponseSuccessDto<>(true, "Motivo de cancelación encontrado", motivoCancelacion);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/motivocancelacion")
    public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> create( @Valid @RequestBody MotivoCancelacionRequestDto dto) {
        MotivoCancelacion motivoCancelacion = service.create(service.fromDto(dto));
        ApiResponseSuccessDto<MotivoCancelacion> resp =
                new ApiResponseSuccessDto<>(true, "Motivo de cancelación creado correctamente", motivoCancelacion);
        return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }

    @PutMapping("/motivocancelacion/{id}")
    public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> update(@PathVariable("id") Long id,@Valid @RequestBody MotivoCancelacionRequestDto dto) {
        MotivoCancelacion motivoEntity = service.fromDto(dto);
        MotivoCancelacion actualizado = service.update(id, motivoEntity);
        ApiResponseSuccessDto<MotivoCancelacion> resp =
                new ApiResponseSuccessDto<>(true, "Motivo de cancelación actualizado correctamente", actualizado);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("/motivocancelacion/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteMotivoCancelacion(@PathVariable("id") Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Motivo de cancelación eliminado correctamente", "Id: " + id);
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/nombre/{nombreMotivo}")
    public ResponseEntity<ApiResponseSuccessDto<List<MotivoCancelacion>>> getMotivoCancelacionByNombre(@PathVariable String nombreMotivo) {
        List<MotivoCancelacion> lista = service.findByNombre(nombreMotivo);
        ApiResponseSuccessDto<List<MotivoCancelacion>> resp = new ApiResponseSuccessDto<>(true,lista.isEmpty() ? "No hay motivos de cancelación disponibles" : "Lista de motivos de cancelación",lista);
        return ResponseEntity.ok(resp);
    }
    
    @GetMapping("/descripcion/{descripcion}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> getMotivoCancelacionCountByDescripcion(@PathVariable String descripcion) {
        long cantidad = service.countByDescripcion(descripcion);
        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true, cantidad == 0 ? "No existen motivos de cancelación con esa descripcion" : "Cantidad de motivos de cancelación encontrados", cantidad);
        return ResponseEntity.ok(resp);
    }

            
}
