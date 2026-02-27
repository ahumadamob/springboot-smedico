package com.imb2025.smedico.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.EspecialidadRequestDto;
import com.imb2025.smedico.dto.response.EspecialidadResponseDto;
import com.imb2025.smedico.entity.Especialidad;
import com.imb2025.smedico.mapper.EspecialidadMapper;
import com.imb2025.smedico.service.IEspecialidadService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/especialidad")
public class EspecialidadController {

        @Autowired
        private IEspecialidadService service;
               

        @GetMapping
        public ResponseEntity<ApiResponseSuccessDto<List<EspecialidadResponseDto>>> findAllEspecialidad() {        		
                List<Especialidad> lista = service.findAll();
                List<EspecialidadResponseDto> listaResponse = new ArrayList<EspecialidadResponseDto>();
                EspecialidadMapper mapper = new EspecialidadMapper();
                for(Especialidad e: lista) {
                	EspecialidadResponseDto dto = new EspecialidadResponseDto();
                	dto = mapper.toDto(e);
                	listaResponse.add(dto);
                }
                
                ApiResponseSuccessDto<List<EspecialidadResponseDto>> resp;
                if (lista.isEmpty()) {
                        resp = new ApiResponseSuccessDto<>(true, "No hay especialidades disponibles", listaResponse);
                } else {
                        resp = new ApiResponseSuccessDto<>(true, "Lista de especialidades", listaResponse);
                }
                return ResponseEntity.ok(resp);
        }

        @GetMapping("/{idespecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<EspecialidadResponseDto>> findEspecialidadById(@PathVariable("idespecialidad") Long id) {
                Especialidad especialidad = service.findById(id);
                EspecialidadMapper mapper = new EspecialidadMapper();
                EspecialidadResponseDto dto = new EspecialidadResponseDto();
                dto= mapper.toDto(especialidad);
                ApiResponseSuccessDto<EspecialidadResponseDto> resp = new ApiResponseSuccessDto<>(true, "Especialidad encontrada",
                                dto);
                return ResponseEntity.ok(resp);
        }

        /*@GetMapping("/nombre/{nombreEspecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<List<Especialidad>>> getEspecialidadByNombre(
                        @PathVariable String nombreEspecialidad) {
                List<Especialidad> lista = service.findByNombre(nombreEspecialidad);
                ApiResponseSuccessDto<List<Especialidad>> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidades encontradas", lista);
                return ResponseEntity.ok(resp);
        }
        */
        
       

        @PostMapping
        public ResponseEntity<ApiResponseSuccessDto<Especialidad>> create(@Valid @RequestBody EspecialidadRequestDto dto) {
        		
        		EspecialidadMapper mapper = new EspecialidadMapper();
                Especialidad especialidad = mapper.fromDto(dto);
                ApiResponseSuccessDto<Especialidad> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidad creada correctamente", especialidad);
                return ResponseEntity.status(HttpStatus.CREATED).body(resp);
        }
        
       
        
        @GetMapping("/nombre/{nombreEspecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<List<EspecialidadResponseDto>>> getEspecialidadByNombre(
                @PathVariable String nombreEspecialidad) {            
            // 1. Buscamos las entidades en el servicio
            List<Especialidad> lista = service.findByNombre(nombreEspecialidad);            
            // 2. Preparamos la lista de respuesta (DTOs)
            List<EspecialidadResponseDto> listaResponse = new ArrayList<>();
            EspecialidadMapper mapper = new EspecialidadMapper();
            // 3. Convertimos cada Entidad a DTO usando el mapper
            for (Especialidad e : lista) {
                listaResponse.add(mapper.toDto(e));
            }
            // 4. Verificamos si la lista está vacía para el mensaje (opcional, pero buena práctica)
            String mensaje = listaResponse.isEmpty() ? "No se encontraron especialidades" : "Especialidades encontradas";
            // 5. Envolvemos la lista de DTOs (NO la de entidades) en el ApiResponse
            ApiResponseSuccessDto<List<EspecialidadResponseDto>> resp = new ApiResponseSuccessDto<>(
                    true,
                    mensaje, 
                    listaResponse
            );            
            return ResponseEntity.ok(resp);
        }        
        @GetMapping("/alias/{textoAlias}")
        public ResponseEntity<ApiResponseSuccessDto<List<EspecialidadResponseDto>>> 
        getfindByAliasContainingIgnoreCase(@PathVariable String alias){
        	
        	List<Especialidad> lista = service.findByAliasContainingIgnoreCase(alias);
        	List<EspecialidadResponseDto> especialidadResponseDto = new ArrayList<>();
        	EspecialidadMapper mapper = new EspecialidadMapper();
        	
        	 for (Especialidad e : lista) {
        		 especialidadResponseDto.add(mapper.toDto(e));
             }
        	 
        	 String mensaje = especialidadResponseDto.isEmpty() ?
        	"No se encontro ese alias" : "Alias encontrado";
        	 
        	 ApiResponseSuccessDto<List<EspecialidadResponseDto>> resp = new 
             ApiResponseSuccessDto<>(
                     true,
                     mensaje, 
                     especialidadResponseDto
             );
             
             return ResponseEntity.ok(resp);
        }
        
        @GetMapping("/atributo-true")
        public ResponseEntity<ApiResponseSuccessDto<List<EspecialidadResponseDto>>> getfindByListarTrue(){
        		List<Especialidad> especialidades = service.findByListarTrue();
        		EspecialidadMapper mapper = new EspecialidadMapper();
        		List<EspecialidadResponseDto> especialidadesDtos = new ArrayList<>();
        		
        		for (Especialidad e : especialidades) {
        			
        			especialidadesDtos.add(mapper.toDto(e));
        		}
        		
        		 String mensaje;
        	        if(especialidadesDtos.isEmpty()){
        	            mensaje = "No hay Especialidades disponibles";
        	        } else {
        	            mensaje = "Lista de Especialidades obtenidas correctamente";
        	        }
        	        ApiResponseSuccessDto<List<EspecialidadResponseDto>> resp =
        	                new ApiResponseSuccessDto<>(true, mensaje, especialidadesDtos);
        		
        	
        	     return ResponseEntity.ok(resp);
        }
        
        
        
        
        @GetMapping("/atributo-false")
        public ResponseEntity<ApiResponseSuccessDto<List<EspecialidadResponseDto>>> getfindByListarFalse(){
        	List<Especialidad> especialidades = service.findByListarFalse();
        	EspecialidadMapper mapper = new EspecialidadMapper();
        	List<EspecialidadResponseDto> especialidadesDtos = new ArrayList<>();
        	
        	for(Especialidad e : especialidades) {
        		
        		especialidadesDtos.add(mapper.toDto(e));
        	}
        	String mensaje;
	        if(especialidadesDtos.isEmpty()){
	            mensaje = "No hay Especialidades disponibles false";
	        } else {
	            mensaje = "Lista de Especialidades obtenidas correctamente";
	        }
	        ApiResponseSuccessDto<List<EspecialidadResponseDto>> resp =
	                new ApiResponseSuccessDto<>(true, mensaje, especialidadesDtos);
		
	        return ResponseEntity.ok(resp);
        	}
        
        

        @GetMapping("/descripcion/{descripcion}")
        public ResponseEntity<ApiResponseSuccessDto<Long>> getEspecialidadCountByDescripcion(
                        @PathVariable String descripcion) {
                long cantidad = service.countByDescripcion(descripcion);
                ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true,
                                "Cantidad de especialidades encontradas", cantidad);
                return ResponseEntity.ok(resp);
        }
        
       
        
        
        
       
        @PutMapping("/{idespecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<Especialidad>> update(@Valid @RequestBody EspecialidadRequestDto dto,
                        @PathVariable("idespecialidad") Long id) {
        		EspecialidadMapper mapper = new EspecialidadMapper();
        		Especialidad especialidadEntity = mapper.fromDto(dto);
                Especialidad actualizado = service.update(id, especialidadEntity);
                ApiResponseSuccessDto<Especialidad> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidad actualizada correctamente", actualizado);
                return ResponseEntity.ok(resp);
        }

        @DeleteMapping("/{idespecialidad}")
        public ResponseEntity<ApiResponseSuccessDto<String>> deleteEspecialidad(
                        @PathVariable("idespecialidad") Long id) {
                service.deleteById(id);
                ApiResponseSuccessDto<String> resp = new ApiResponseSuccessDto<>(true,
                                "Especialidad eliminada correctamente", "Id: " + id);
                return ResponseEntity.ok(resp);
        }

}
