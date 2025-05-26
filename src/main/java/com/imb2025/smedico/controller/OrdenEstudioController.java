package com.imb2025.smedico.controller;

import java.util.List;  


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.imb2025.smedico.entity.*;
import com.imb2025.smedico.service.IOrdenEstudioService;


@RestController
public class OrdenEstudioController {

	@Autowired
	private IOrdenEstudioService service; 
    
	// GET - Obtener todos las ordenes de estudio
	@GetMapping("/ordenestudio")
	public List<OrdenEstudio> findAllOrdenEstudio() {
		return service.findAll();
	}
	
	// GET - Obtener de orden de estudio por ID 
	@GetMapping("/ordenestudio/{idordenestudio}")
	public OrdenEstudio findOrdenEstudioById(@PathVariable("idordenestudio") Long id) {
		return service.findById(id);
	}
	
	// POST - Crear nueva orden de estudio
	@PostMapping("/ordenestudio")
	public OrdenEstudio createOrdenEstudio(@RequestBody OrdenEstudio ordenestudio ) {
	  return service.save(ordenestudio);	  
    }
	
	// PUT - Actualizar orden de estudio
	@PutMapping("/ordenestudio/{idordenestudio}")
	public OrdenEstudio updateOrdenEstudio(@PathVariable("idordenestudio") Long idordenestudio, @RequestBody OrdenEstudio datosOrdenEstudio) {
		OrdenEstudio ordenEstudioExistente = service.findById(idordenestudio);
		
		// Validación 
		if (ordenEstudioExistente == null) {
			throw new RuntimeException("Orden Estudio no encontrado con ID:"+ idordenestudio);
		}
		
		// Actualizamos campos
		ordenEstudioExistente.setFecha(datosOrdenEstudio.getFecha());
		ordenEstudioExistente.setMedicoId(datosOrdenEstudio.getMedicoId());
		ordenEstudioExistente.setPacienteId(datosOrdenEstudio.getPacienteId());
		ordenEstudioExistente.setEstudioId(datosOrdenEstudio.getEstudioId());
		
		return service.save(ordenEstudioExistente);
	}
	
	// DELETE - Eliminar Orden Estudio
	@DeleteMapping("/ordenestudio/{idordenestudio}")
	public String deleteOrdenEstudio(@PathVariable("idordenestudio") Long id) {
		service.deleteById(id);
		return "Orden Estudio " + id + " eliminado correctamente";
	}
	
	
}


