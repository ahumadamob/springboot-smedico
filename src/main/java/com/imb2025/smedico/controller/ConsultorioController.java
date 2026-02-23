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
	private IConsultorioService service;
	
	//Crear Consultorio - POST
	@PostMapping("/consultorio")
	public ResponseEntity<ApiResponseSuccessDto<ConsultorioResponseDto>> create(@Valid @RequestBody ConsultorioRequestDto consultorioRequestDto) throws Exception{
		ConsultorioMapper mapper = new ConsultorioMapper();
		Consultorio consultorio = service.create(mapper.fromDto(consultorioRequestDto));
		ConsultorioResponseDto responseDto = mapper.toDto(consultorio);
		ApiResponseSuccessDto<ConsultorioResponseDto> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio creado correctamente", responseDto);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}
	
	//Buscar por ID - GET (por ID)
    @GetMapping("/consultorio/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultorioResponseDto>> findById(@PathVariable("id") Long id) {
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	Consultorio consultorio = service.findById(id);
    	ConsultorioResponseDto responseDto = mapper.toDto(consultorio);
    	ApiResponseSuccessDto<ConsultorioResponseDto> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio encontrado correctamente", responseDto);
		return ResponseEntity.ok(respuesta);
    }
    
    //Buscar por nombre
    @GetMapping("/consultorio/nombre/{nombre}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultorioResponseDto>> findByNombre(@PathVariable String nombre){
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	Consultorio consultorio = service.findByNombre(nombre);
    	if (consultorio == null) {
            throw new RuntimeException("Consultorio no encontrado con nombre: " + nombre);
        }
    	ConsultorioResponseDto responseDto = mapper.toDto(consultorio);
    	ApiResponseSuccessDto<ConsultorioResponseDto> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio encontrado correctamente", responseDto);
    	return ResponseEntity.ok(respuesta);
    }
	
    //Buscar por ubicación
    @GetMapping("/consultorio/ubicacion/{ubicacion}")
    public ResponseEntity<ApiResponseSuccessDto<List<Consultorio>>> findByUbicacion(@PathVariable String ubicacion){
    	List<Consultorio> consultorio = service.findByUbicacion(ubicacion);
    	ApiResponseSuccessDto<List<Consultorio>> respuesta;
    	if(consultorio.isEmpty()) {
    		respuesta = new ApiResponseSuccessDto<>(true, "No existe consultorio en esa ubicación", consultorio);
    	}else {
    		respuesta = new ApiResponseSuccessDto<>(true, "Consultorios", consultorio);
    	}
    	return ResponseEntity.ok(respuesta);
    }
    
	//Buscar lista - GET 
    @GetMapping("/consultorio")
    public ResponseEntity<ApiResponseSuccessDto<List<Consultorio>>> findAllConsultorio() {
    	List<Consultorio> consultorio = service.findAll();
    	ApiResponseSuccessDto<List<Consultorio>> respuesta;
    	if(consultorio.isEmpty()) {
    		respuesta = new ApiResponseSuccessDto<>(true, "El consultorio no existe", consultorio);
    	}else {
    		respuesta = new ApiResponseSuccessDto<>(true, "Consultorios", consultorio);
    	}
    	return ResponseEntity.ok(respuesta);
    }
   
    //Eliminar por ID - DELETE
    @DeleteMapping("/consultorio/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> delete(@PathVariable("id") Long id) {
    	ConsultorioMapper mapper = new ConsultorioMapper();
        Consultorio consultorio = service.findById(id);
        if (consultorio == null) {
        	ApiResponseSuccessDto<String> respuesta = new ApiResponseSuccessDto<>
        	(false, "Consultorio" + id + "no encontrado", null);
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(respuesta);
        }
        service.deleteById(id);
        ApiResponseSuccessDto<String> respuesta = new ApiResponseSuccessDto<>
    	(true, "Consultorio eliminado correctamente", "ID eliminado: " + id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(respuesta);
    }
    
    //Actualizar consultorio - PUT
    @PutMapping("/consultorio/{id}")
    public ResponseEntity<ApiResponseSuccessDto<ConsultorioResponseDto>> update(@PathVariable() Long id, @Valid @RequestBody ConsultorioRequestDto consultorioRequestDto) {
    	ConsultorioMapper mapper = new ConsultorioMapper();
    	Consultorio consultorio = mapper.fromDto(consultorioRequestDto);
    	Consultorio actualizar = service.update(id, consultorio);
    	ConsultorioResponseDto responseDto = mapper.toDto(actualizar);
    	ApiResponseSuccessDto<ConsultorioResponseDto> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio actualizado", responseDto);
    	return ResponseEntity.ok(respuesta);
    }  
}
