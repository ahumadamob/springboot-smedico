package com.imb2025.smedico.controller;

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
import com.imb2025.smedico.service.IConsultorioService;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.ConsultorioRequestDto;


@RestController
public class ConsultorioController {
	
	@Autowired
	private IConsultorioService servicio;
	
	//Crear Consultorio - POST
	@PostMapping("/consultorio")
	public ResponseEntity<ApiResponseSuccessDto<Consultorio>> create(@Valid @RequestBody ConsultorioRequestDto consultorioRequestDto) throws Exception{
		Consultorio consultorio = servicio.create(servicio.fromDto(consultorioRequestDto));
		ApiResponseSuccessDto<Consultorio> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio creado correctamente", consultorio);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	}
	
	//Buscar por ID - GET (por ID)
    @GetMapping("/consultorio/{id}")
    public ResponseEntity<ApiResponseSuccessDto<Consultorio>> findConsultorioById(@PathVariable("id") Long id) {
    	Consultorio consultorio = servicio.findById(id);
		ApiResponseSuccessDto<Consultorio> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio encontrado correctamente", consultorio);
		return ResponseEntity.ok(respuesta);
    }
    
    //Buscar por nombre
    @GetMapping("/consultorio/nombre/{nombre}")
    public ResponseEntity<ApiResponseSuccessDto<Consultorio>> findByNombre(@PathVariable String nombre){
    	Consultorio consultorio = servicio.findByNombre(nombre);
    	ApiResponseSuccessDto<Consultorio> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio encontrado correctamente", consultorio);
    	return ResponseEntity.ok(respuesta);
    }
	
    //Buscar por ubicación
    @GetMapping("/consultorio/ubicacion/{ubicacion}")
    public ResponseEntity<ApiResponseSuccessDto<List<Consultorio>>> findByUbicacion(@PathVariable String ubicacion){
    	List<Consultorio> consultorio = servicio.findByUbicacion(ubicacion);
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
    	List<Consultorio> consultorio = servicio.findAll();
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
    public ResponseEntity<ApiResponseSuccessDto<Consultorio>> update(@PathVariable("id") Long id, @Valid @RequestBody ConsultorioRequestDto consultorioRequestDto) throws Exception{
    	Consultorio consultorio = servicio.fromDto(consultorioRequestDto);
    	Consultorio actualizar = servicio.update(id, consultorio);
    	ApiResponseSuccessDto<Consultorio> respuesta = new ApiResponseSuccessDto<>(true, "Consultorio actualizado", actualizar);
    	return ResponseEntity.ok(respuesta);
    }  
}
