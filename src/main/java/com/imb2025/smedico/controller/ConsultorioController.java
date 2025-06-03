package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
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
	public Consultorio create(@RequestBody ConsultorioRequestDTO consultorioRequestDto){
		try {
			return servicio.create(servicio.fromDto(consultorioRequestDto));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//Buscar por ID - GET (por ID)
    @GetMapping("/consultorio/{id}")
    public Consultorio findConsultorioById(@PathVariable("id") Long id) {
        return servicio.findById(id);
    }
    
    //Listar Consultorios - GET
    @GetMapping("/consultorio")
    public List<Consultorio> findAllConsultorios(){
    	return servicio.findAll();
    }
    
    //Eliminar por ID - DELETE
    @DeleteMapping("/consultorio/{id}")
    public String delete(@PathVariable("id") Long id) {
		servicio.deleteById(id);
		return "Consultorio " + id.toString() + " eliminado correctamente. ";
	}
       
    //Actualizar consultorio - PUT
    @PutMapping("/consultorio/{id}")
    public Consultorio update(@RequestBody ConsultorioRequestDTO dto,@PathVariable("id") Long id) {
    	

        try {
            return servicio.update(id,servicio.fromDto(dto));
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("message", "El consultorio " + id + " no existe");
            return null;
        }
       
    }
}