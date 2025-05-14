package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.imb2025.smedico.entity.Consultorio;
import com.imb2025.smedico.service.IConsultorioService;


@RestController
@RequestMapping("/consultorios")
public class ConsultorioController {
	
	@Autowired
	private IConsultorioService servicio;
	
	//Crear Consultorio
	@PostMapping
	public Consultorio crear(@RequestBody Consultorio consultorio){
		return servicio.guardarConsultorio(consultorio);
	}
	
	//Buscar por ID
    @GetMapping("/{id}")
    public Consultorio buscarPorId(@PathVariable Long id) {
        return servicio.buscarPorId(id);
    }
    
    //Buscar por nombre
    @GetMapping("/buscarPorNombre")
    public List<Consultorio> buscarPorNombre(@RequestParam String nombre) {
        return servicio.buscarPorNombre(nombre);
    }
    
    //Listar todos
    @GetMapping
    public List<Consultorio> listarTodos() {
        return servicio.listarTodos();
    }
    
    //Eliminar por ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPorId(@PathVariable Long id) {
        servicio.eliminarPorId(id);
        return ResponseEntity.ok().build();
    }
    
    //Eliminar por nombre
    @DeleteMapping("/eliminarPorNombre")
    public ResponseEntity<Void> eliminarPorNombre(@RequestParam String nombre) {
        servicio.eliminarPorNombre(nombre);
        return ResponseEntity.ok().build();
    }
    
    //Actualizar consultorio
    @PutMapping("/{id}")
    public Consultorio actualizar(@PathVariable Long id, @RequestBody Consultorio consultorio) {
    	return servicio.guardarConsultorio(consultorio);
    }
}