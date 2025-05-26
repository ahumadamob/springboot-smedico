package com.imb2025.smedico.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.EstudioRequestDTO;
import com.imb2025.smedico.dto.EstudioResponseDTO;
import com.imb2025.smedico.entity.Estudio;
import com.imb2025.smedico.service.IEstudioService;

@RestController
public class EstudioControler {

	@Autowired
	private IEstudioService service;
	
	@GetMapping("/estudio")
	public List<Estudio>findAllestudio(){
		return service.findAll();
	}
	@GetMapping("/estudio/{idestudio}")
	public Estudio findEstudioByid(@PathVariable("idestudio") Long id) {
		return service.findById(id);
	}
	@GetMapping("/estudioCompleto")
	public List<EstudioResponseDTO> findAllestudio1() {
	    return service.findAll().stream()
	        .map(EstudioResponseDTO::new)
	        .collect(Collectors.toList());
	}
	
	@PostMapping("/estudioid")
	public Estudio saveEstudio(@RequestBody  EstudioRequestDTO dto) throws Exception {
		return service.create(dto);
	}
	
	@PutMapping("/estudio/{idestudio}")
	public Estudio updateEstudio(@PathVariable("idestudio") Long id, @RequestBody EstudioRequestDTO dto) throws Exception {
		return service.update(dto, id);
	}
	
	@DeleteMapping("/estudio/{idestudio}")
	public String deleteEstudio(@PathVariable("idestudio") Long id) {
		service.deleteById(id);
		return "Estudio "+id.toString()+ " Eliminado Correctamente";
	}
}
