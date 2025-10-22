package com.imb2025.smedico.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.ResultadoEstudioRequestDto;
import com.imb2025.smedico.dto.response.ResultadoEstudioResponseDto;
import com.imb2025.smedico.entity.ResultadoEstudio;
import com.imb2025.smedico.mapper.ResultadoEstudioMapper;
import com.imb2025.smedico.service.IResultadoEstudioService;

import jakarta.validation.Valid;

@RestController
public class ResultadoEstudioController {

	@Autowired
	private IResultadoEstudioService service;
	
	
	@GetMapping("/ResultadoEstudio")
	public ResponseEntity<ApiResponseSuccessDto<List<ResultadoEstudioResponseDto>>>findAll(){
		List<ResultadoEstudio> resultadoEstudio = service.findAll();
		List<ResultadoEstudioResponseDto> listaResponse = new ArrayList<ResultadoEstudioResponseDto>();
		ResultadoEstudioMapper mapper = new ResultadoEstudioMapper();
		for(ResultadoEstudio r: resultadoEstudio) {
			ResultadoEstudioResponseDto dto = new ResultadoEstudioResponseDto();
			dto = mapper.toDto(r);
			listaResponse.add(dto);
		}
				
		ApiResponseSuccessDto<List<ResultadoEstudioResponseDto>> resp;
		
		if(resultadoEstudio.isEmpty()) {
			
			resp = new ApiResponseSuccessDto<>(true,"No hay Estudios disponibles",listaResponse);
        }else {
            resp = new ApiResponseSuccessDto<>(true,"Lista de Estudios",listaResponse);
	}
		return ResponseEntity.ok(resp);
	}
	
	
	@GetMapping("/ResultadoEstudio/{idResultadoEstudio}")
	public ResponseEntity<ApiResponseSuccessDto<ResultadoEstudioResponseDto>> findById(@PathVariable("idResultadoEstudio") long id) {

		ResultadoEstudio resultadoEstudio = service.findById(id);
		ResultadoEstudioMapper mapper = new ResultadoEstudioMapper();
		ResultadoEstudioResponseDto dto = new ResultadoEstudioResponseDto();
		dto = mapper.toDto(resultadoEstudio);
		
		ApiResponseSuccessDto<ResultadoEstudioResponseDto> resp =
				new ApiResponseSuccessDto<>(true,"Estudio encontrado",dto);
		
		return ResponseEntity.ok(resp);
	}

	
	
	@PostMapping("/ResultadoEstudio")
	public ResponseEntity<ApiResponseSuccessDto<ResultadoEstudio>> createResultadoEstudio(@Valid @RequestBody ResultadoEstudioRequestDto requestDto) {
		ResultadoEstudioMapper mapper = new ResultadoEstudioMapper();
		ResultadoEstudio resultadoEstudio = mapper.fromDto(requestDto);
		ApiResponseSuccessDto<ResultadoEstudio> resp =
				new ApiResponseSuccessDto<>(true,"Estudio creado correctamente",resultadoEstudio);
		return ResponseEntity.status(HttpStatus.CREATED).body(resp);
		
	}
	
	
	@PutMapping ("/ResultadoEstudio/{id}")
	public ResponseEntity<ApiResponseSuccessDto<ResultadoEstudio>>  updateResultadoEstudio(@PathVariable Long id,
		@Valid @RequestBody ResultadoEstudioRequestDto requestDto) {
		ResultadoEstudioMapper mapper = new ResultadoEstudioMapper();
		ResultadoEstudio resultadoEstudioEntity = mapper.fromDto(requestDto);
		ResultadoEstudio actualizado = service.update(id, resultadoEstudioEntity);
		ApiResponseSuccessDto<ResultadoEstudio> resp =
				new ApiResponseSuccessDto<>(true,"Estudio actualizado correctamente",actualizado);
		return ResponseEntity.ok(resp);
	}
	
	@DeleteMapping ("/ResultadoEstudio/{idResultadoEstudio}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteResultadoEstudio (@PathVariable("idResultadoEstudio") Long id) {
		service.deleteById(id);
		ApiResponseSuccessDto<String> resp =
				new ApiResponseSuccessDto<>(true,"Estudio eliminado correctamente", "Id: " + id);
		
		return ResponseEntity.ok(resp);	
		}
	
	
	@GetMapping("/ResultadoEstudio/fecha/{fecha}")
	public ResponseEntity<ApiResponseSuccessDto<List<ResultadoEstudio>>> getResultadoEstudioPorFecha(@PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCarga){
			List<ResultadoEstudio> lista = service.findByFechaCarga(fechaCarga);
			ApiResponseSuccessDto<List<ResultadoEstudio>> resp;
			
			 if (lista.isEmpty()) {
			        resp = new ApiResponseSuccessDto<>(true, "No hay estudios en la fecha " + fechaCarga, lista);
			    } else {
			        resp = new ApiResponseSuccessDto<>(true, "Lista de estudios en la fecha " + fechaCarga, lista);
			    }
			 
			 return ResponseEntity.ok(resp);
	
		}
	
	@GetMapping("/ResultadoEstudio/count/{fecha}")
	public ResponseEntity<ApiResponseSuccessDto<Long>> countResultadoEstudioByFecha(
	        @PathVariable("fecha") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fechaCarga) {
		
		long cantidad = service.countByFechaCarga(fechaCarga);
		
		ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(
	            true,
	            "Cantidad de estudios en la fecha " + fechaCarga,
	            cantidad
	    );

	    return ResponseEntity.ok(resp);
	}
	
	
	@GetMapping("/ResultadoEstudio/True")
	public ResponseEntity<ApiResponseSuccessDto<List<ResultadoEstudioResponseDto>>> listarTrue() {
		
		List<ResultadoEstudio> resultados = service.listarTrue();
		ResultadoEstudioMapper mapper = new ResultadoEstudioMapper();
		List<ResultadoEstudioResponseDto> dto = new ArrayList<>();
		
		for(ResultadoEstudio re : resultados) {
			
			dto.add(mapper.toDto(re));
		}
		
		return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Resultados con AtributoBooleano:  True", dto));
	}
	
	@GetMapping("/ResultadoEstudio/False")
	public ResponseEntity<ApiResponseSuccessDto<List<ResultadoEstudioResponseDto>>> listarFalse() {
		
		List<ResultadoEstudio> resultados = service.listarFalse();
		ResultadoEstudioMapper mapper = new ResultadoEstudioMapper();
		List<ResultadoEstudioResponseDto> dto = new ArrayList<>();
		
		for(ResultadoEstudio re : resultados) {
			
			dto.add(mapper.toDto(re));
		}
		
		return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Resultados con AtributoBooleano:  False", dto));
	}
	
	
	}




