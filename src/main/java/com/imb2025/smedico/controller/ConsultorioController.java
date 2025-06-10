package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import dto.ConsultorioRequestDTO;

@RestController
public class ConsultorioController {
	
	@Autowired
	private IConsultorioService servicio;
	
	//Crear Consultorio - POST
	@PostMapping("/consultorio")
	public ResponseEntity<Consultorio> create(@RequestBody ConsultorioRequestDTO consultorioRequestDto) throws Exception{
		return ResponseEntity.ok(servicio.create(servicio.fromDto(consultorioRequestDto)));
	}
		
	//Buscar por ID - GET (por ID)
    @GetMapping("/consultorio/{id}")
    public ResponseEntity<Consultorio> findConsultorioById(@PathVariable("id") Long id) {
    	Consultorio consultorio = new Consultorio();
    	consultorio = servicio.findById(id);
    	if(consultorio == null) {
    		return ResponseEntity.noContent().build();
    	}else {
    		return ResponseEntity.ok(consultorio);
    	}
    }
    
    //Listar Consultorios - GET
    @GetMapping("/consultorio")
    public ResponseEntity<List<Consultorio>> findAllConsultorios(){
    	List<Consultorio> listaConsultorio = servicio.findAll();
    	if (listaConsultorio.isEmpty()) {
    		return ResponseEntity.noContent().build();
    	}else {
    		return ResponseEntity.ok(listaConsultorio);
    	}
    }
    
    //Eliminar por ID - DELETE
    @DeleteMapping("/consultorio/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") Long id) {
    	return ResponseEntity.ok("Consultorio " + id.toString() + " eliminado correctamente. ");
	}
       
    //Actualizar consultorio - PUT
    @PutMapping("/consultorio/{id}")
    public ResponseEntity<Consultorio> update(@RequestBody ConsultorioRequestDTO dto,@PathVariable("id") Long id) throws Exception{
    	Consultorio consultorio = new Consultorio();
    	consultorio = servicio.fromDto(dto);
    	return ResponseEntity.ok(servicio.update(id, consultorio));
       
    }
    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGlobalExceptions(Exception e){
    	return ResponseEntity.badRequest().body(e.getMessage());
    }
}