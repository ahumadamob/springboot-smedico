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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.imb2025.smedico.dto.AfiliacionRequestDTO;

import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.service.IAfiliacionService;

@RestController
@RequestMapping("/Afiliacion")
public class AfiliacionController {

	@Autowired
	private IAfiliacionService servi;

	@GetMapping
	public ResponseEntity<List<Afiliacion>> findAllAfiliacion() throws Exception {
		List<Afiliacion> lista = servi.findAll();
		if (lista.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(lista);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Afiliacion> findAfiliacionById(@PathVariable("id") Long idAfiliacion) throws Exception  {
		Afiliacion afiliacion = new Afiliacion();
		afiliacion = servi.findById(idAfiliacion);
		if (afiliacion == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(afiliacion);
		}
	}

	@PostMapping
	public ResponseEntity<Afiliacion> createAfiliacion(@RequestBody AfiliacionRequestDTO dto) throws Exception {
		try {
			return ResponseEntity.ok(servi.create(servi.fromDto(dto)));
		} catch (Exception e) {
			System.err.println("Error al crear la afiliacion: " + e.getMessage());
			throw new RuntimeException("Hubo un error: " + e.getMessage(), e);
		}
	}

	@PutMapping("/{id}")
	public Afiliacion updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDTO dto) throws Exception {
		return servi.update(id, servi.fromDto(dto));

	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAfiliacion(@PathVariable Long id) throws Exception {
		servi.deleteById(id);
		return ResponseEntity.ok("Afiliacion " + id.toString() + " eliminada correctamente.");
	}
	
	@ExceptionHandler (Exception.class)
	public ResponseEntity<String> handleGlobalException (Exception ex){
		ex.printStackTrace();
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}
