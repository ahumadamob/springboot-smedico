package com.imb2025.smedico.controller;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.imb2025.smedico.entity.Consultorio;
import com.imb2025.smedico.mapper.ConsultorioMapper;
import com.imb2025.smedico.service.IConsultorioService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.ConsultorioRequestDto;
import com.imb2025.smedico.dto.response.ConsultorioResponseDto;


@RestController
public class ConsultorioController {
	
	@Autowired
	private IConsultorioService servicio;
	
	//Crear Consultorio - POST
	@PostMapping("/consultorio")
	public ResponseEntity<ApiResponseSuccessDto<Consultorio>> create(@Valid @RequestBody ConsultorioRequestDto dto) {
		ConsultorioMapper mapper = new ConsultorioMapper();
		Consultorio consultorio = servicio.create(mapper.fromDto(dto));
		ApiResponseSuccessDto<Consultorio> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio creado correctamente", consultorio);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}
	
	//Buscar por ID - GET (por ID)
    @GetMapping("/consultorio/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultorioResponseDto>> findConsultorioById(@PathVariable("id") Long id) {
    	Consultorio consultorio = servicio.findById(id);
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	ConsultorioResponseDto dto = new ConsultorioResponseDto();
    	dto = mapper.toDto(consultorio);
		ApiResponseSuccessDto<ConsultorioResponseDto> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio encontrado correctamente", dto);
		return ResponseEntity.ok(respuesta);
    }
    
    //Buscar por nombre
    @GetMapping("/consultorio/nombre/{nombre}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultorioResponseDto>> findByNombre(@PathVariable String nombre){
    	Consultorio consultorio = servicio.findByNombre(nombre);
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	ConsultorioResponseDto dto = new ConsultorioResponseDto();
    	dto = mapper.toDto(consultorio);
    	ApiResponseSuccessDto<ConsultorioResponseDto> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio encontrado correctamente", dto);
    	return ResponseEntity.ok(respuesta);
    }
	
    //Buscar por ubicación
    @GetMapping("/consultorio/ubicacion/{ubicacion}")
    public ResponseEntity<ApiResponseSuccessDto<List<ConsultorioResponseDto>>> findByUbicacion(@PathVariable String ubicacion){
    	List<Consultorio> consultorio = servicio.findByUbicacion(ubicacion);
    	List<ConsultorioResponseDto> listConsultorioResponse = new ArrayList<ConsultorioResponseDto>();
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	ConsultorioResponseDto dto = new ConsultorioResponseDto();
    	ApiResponseSuccessDto<ConsultorioResponseDto> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio encontrado correctamente", dto);
    	
    	for (Consultorio map:consultorio) {
    		ConsultorioResponseDto dto1 = new ConsultorioResponseDto();
    		dto1 = mapper.toDto(map);
    		listConsultorioResponse.add(dto1);
        }
    	ApiResponseSuccessDto<List<ConsultorioResponseDto>> respuesta1 = new ApiResponseSuccessDto<>(true, 
    			listConsultorioResponse.isEmpty() ? "No existe consultorio en esa ubicación" :
					"Consultorios" ,listConsultorioResponse);

    	return ResponseEntity.ok(respuesta1);
    }
    
	//Buscar lista - GET 
    @GetMapping("/consultorio")
    public ResponseEntity<ApiResponseSuccessDto<List<ConsultorioResponseDto>>> findAllConsultorio() {
    	List<Consultorio> consultorio = servicio.findAll();
    	List<ConsultorioResponseDto> listConsultorioResponse = new ArrayList<ConsultorioResponseDto>();
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	
    	for(Consultorio map:consultorio) {
    		ConsultorioResponseDto dto = new ConsultorioResponseDto();
    		dto = mapper.toDto(map);
    		listConsultorioResponse.add(dto);
    	}
    	
    	ApiResponseSuccessDto<List<ConsultorioResponseDto>> respuesta = new ApiResponseSuccessDto<>(true, 
    			listConsultorioResponse.isEmpty() ? "No hay consultorios para mostrar" :
    								"Consultorios", listConsultorioResponse);
    	
    	return ResponseEntity.ok(respuesta);
    }
   
    //Eliminar por ID - DELETE
    @DeleteMapping("/consultorio/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
        Consultorio consultorio = servicio.findById(id);
        if (consultorio == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Consultorio " + id.toString() + " no encontrado");
        }
        servicio.deleteById(id);
        return ResponseEntity.ok("Consultorio " + id.toString() + " eliminado correctamente. ");
    }
    
    //Actualizar consultorio - PUT
    @PutMapping("/consultorio/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Consultorio>> update(@PathVariable("id") Long id, @Valid @RequestBody ConsultorioRequestDto dto) throws Exception {
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	Consultorio consultorio = mapper.fromDto(dto);
    	Consultorio actualizar = servicio.update(id, consultorio);
    	ApiResponseSuccessDto<Consultorio> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio actualizado", actualizar);
    	return ResponseEntity.ok(respuesta);
    }  
}
