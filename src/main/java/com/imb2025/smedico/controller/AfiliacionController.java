package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
<<<<<<< Updated upstream
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

>>>>>>> Stashed changes
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

<<<<<<< Updated upstream
	@PostMapping
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
=======
    @GetMapping
    public ResponseEntity<List<Afiliacion>> findAllAfiliacion() {
        List<Afiliacion> afiliaciones = servi.findAll();
        return ResponseEntity.ok(afiliaciones);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Afiliacion> findAfiliacionById(@PathVariable("id") Long idAfiliacion) {
        Afiliacion afiliacion = servi.findById(idAfiliacion);
        return ResponseEntity.ok(afiliacion);
    }

    @PostMapping
    public ResponseEntity<Afiliacion> createAfiliacion(@RequestBody AfiliacionRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
                .orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));

        Afiliacion afiliacion = AfiliacionRequestDTO.fromDTO(dto, paciente, obraSocial);
        Afiliacion created = servi.save(afiliacion);
        return ResponseEntity.status(201).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Afiliacion> updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDTO dto) {
        Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
                .orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

        ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
                .orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));

        Afiliacion afiliacion = AfiliacionRequestDTO.fromDTO(dto, paciente, obraSocial);
        Afiliacion updated = servi.update(id, afiliacion);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAfiliacion(@PathVariable Long id) {
        servi.deleteById(id);
        return ResponseEntity.noContent().build();
    }

 
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
>>>>>>> Stashed changes
}

