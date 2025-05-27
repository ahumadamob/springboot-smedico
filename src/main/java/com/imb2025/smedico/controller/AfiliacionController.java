package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imb2025.smedico.DTO.AfiliacionRequestDTO;
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/Afiliacion")
public class AfiliacionController {

	@Autowired
	private IAfiliacionService servi;

	@GetMapping
	public List<Afiliacion> findAllAfiliacion() {
		return servi.findAll();
	}

	@GetMapping("/{id}")
	public Afiliacion findAfiliacionById(@PathVariable("id") Long idAfiliacion) {
		return servi.findById(idAfiliacion);
	}

	@PostMapping
	public Afiliacion createAfiliacion(@RequestBody AfiliacionRequestDTO dto) throws Exception {
		return servi.create(dto);
	}

	@PutMapping("/{id}")
	public Afiliacion updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDTO dto) throws Exception {
		return servi.update(id, dto);
    
	public Afiliacion createAfiliacion(@RequestBody Afiliacion afiliacion) {
		return servi.save(afiliacion);
	}

	@PutMapping("/{id}")
	public Afiliacion updateAfiliacion(@PathVariable Long id, @RequestBody Afiliacion afiliacion) {
		return servi.update(id, afiliacion);
	}

	@DeleteMapping("/{id}")
	public String deleteAfiliacion(@PathVariable Long id) {
		servi.deleteById(id);
		return "Afiliacion " + id.toString() + " eliminada correctamente.";
	}
}
