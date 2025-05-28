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
import com.imb2025.smedico.entity.Afiliacion;
import com.imb2025.smedico.entity.ObraSocial;
import com.imb2025.smedico.entity.Paciente;
import com.imb2025.smedico.repository.ObraSocialRepository;
import com.imb2025.smedico.repository.PacienteRepository;
import com.imb2025.smedico.service.IAfiliacionService;
import dto.AfiliacionRequestDTO;

@RestController
@RequestMapping("/Afiliacion")
public class AfiliacionController {

	@Autowired
	private IAfiliacionService servi;

	@Autowired
	private PacienteRepository pacienteRepository;

	@Autowired
	private ObraSocialRepository obraSocialRepository;

	@GetMapping
	public List<Afiliacion> findAllAfiliacion() {
		return servi.findAll();
	}

	@GetMapping("/{id}")
	public Afiliacion findAfiliacionById(@PathVariable("id") Long idAfiliacion) {
		return servi.findById(idAfiliacion);
	}

	@PostMapping
	public Afiliacion createAfiliacion(@RequestBody AfiliacionRequestDTO dto) {
		Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
				.orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

		ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
				.orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));

		Afiliacion afiliacion = new Afiliacion();
		afiliacion.setPaciente(paciente);
		afiliacion.setObraSocial(obraSocial);
		afiliacion.setNumeroAfiliado(dto.getNumeroAfiliado());
		afiliacion.setFechaVigenciaDesde(dto.getFechaVigenciaDesde());

		return servi.save(afiliacion);
	}

	@PutMapping("/{id}")
	public Afiliacion updateAfiliacion(@PathVariable Long id, @RequestBody AfiliacionRequestDTO dto) {
		Paciente paciente = pacienteRepository.findById(dto.getPacienteId())
				.orElseThrow(() -> new RuntimeException("Paciente no encontrado"));

		ObraSocial obraSocial = obraSocialRepository.findById(dto.getObraSocialId())
		.orElseThrow(() -> new RuntimeException("Obra Social no encontrada"));

		Afiliacion afiliacion = new Afiliacion();
		afiliacion.setPaciente(paciente);
		afiliacion.setObraSocial(obraSocial);
		afiliacion.setNumeroAfiliado(dto.getNumeroAfiliado());
		afiliacion.setFechaVigenciaDesde(dto.getFechaVigenciaDesde());

		return servi.update(id, afiliacion);
	}

	@DeleteMapping("/{id}")
	public String deleteAfiliacion(@PathVariable Long id) {
		servi.deleteById(id);
		return "Afiliacion " + id.toString() + " eliminada correctamente.";
	}
}