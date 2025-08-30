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
        ApiResponseSuccessDto<List<MotivoCancelacion>> resp;
        if (lista.isEmpty()) {
        	resp = new ApiResponseSuccessDto<>(true,"No hay motivos de cancelacion disponibles",lista);
        }else {
        	resp = new ApiResponseSuccessDto<>(true,"Lista de motivos de cancelacion",lista);
        }
        return ResponseEntity.ok(resp);
	}
	
	@GetMapping("/motivocancelacion/{idmotivocancelacion}")
	public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> findMotivoCancelacionById(@PathVariable("idmotivocancelacion") Long id) {
        MotivoCancelacion motivoCancelacion = service.findById(id);
        ApiResponseSuccessDto<MotivoCancelacion> resp =
        		new ApiResponseSuccessDto<>(true,"Motivo de Cancelacion Encontrado",motivoCancelacion);
        return ResponseEntity.ok(resp);
	}
	
	@PostMapping("/motivocancelacion")
	public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> create(@Valid @RequestBody MotivoCancelacionRequestDto dto) throws Exception {
		MotivoCancelacion motivoCancelacion = service.create(service.fromDto(dto));
		ApiResponseSuccessDto<MotivoCancelacion> resp =
        		new ApiResponseSuccessDto<>(true,"Motivo de Cancelacion Creado Correctamente",motivoCancelacion);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
    }
		
	@PutMapping("/motivocancelacion/{idmotivocancelacion}")
    public ResponseEntity<ApiResponseSuccessDto<MotivoCancelacion>> update(@PathVariable("idmotivocancelacion") Long id,@Valid @RequestBody MotivoCancelacionRequestDto dto) throws Exception {
		MotivoCancelacion motivoEntity = service.fromDto(dto);
	    MotivoCancelacion actualizado = service.update(id, motivoEntity);
	        ApiResponseSuccessDto<MotivoCancelacion> resp =
	            new ApiResponseSuccessDto<>(true,"Motivo de cancelación actualizado correctamente", actualizado);
	        return ResponseEntity.ok(resp);
    }
	
	@DeleteMapping("/motivocancelacion/{idmotivocancelacion}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteMotivoCancelacion(@PathVariable("idmotivocancelacion") Long id) {
        service.deleteById(id);
        ApiResponseSuccessDto<String> resp =
        		new ApiResponseSuccessDto<>(true,"Motivo de Cancelacion Eliminado Correctamente","Id: "+id);
        return ResponseEntity.ok(resp);
    }
}
