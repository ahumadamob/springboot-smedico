package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import com.imb2025.smedico.dto.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.service.IMedioPagoService;

@RestController
@RequestMapping("/mediopago")
public class MedioPagoController {
	
	@Autowired
	private final IMedioPagoService service;
	
	public MedioPagoController(IMedioPagoService service) {
	    this.service = service;
	}
	
    @GetMapping
    public ResponseEntity<List<MedioPago>> findAllMedioPago() {
            return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{idmediopago}")
    public ResponseEntity<MedioPago> findMedioPagoByid(@PathVariable("idmediopago") Long id) {
            MedioPago medioPago = service.findById(id);
            if (medioPago == null) {
                    return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(medioPago);
    }

    @PostMapping
    public ResponseEntity<MedioPago> createMedioPago(@RequestBody MedioPagoRequestDto mediopagoDto) {
            MedioPago medioPago = service.fromDto(mediopagoDto);
            MedioPago creado = service.create(medioPago);
            return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedioPago> updateMedioPago(@PathVariable Long id,
                    @RequestBody MedioPagoRequestDto mediopagoDto) {
            MedioPago medioPago = service.fromDto(mediopagoDto);
            MedioPago actualizado = service.update(id, medioPago);
            if (actualizado == null) {
                    return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedioPago(@PathVariable Long id) {
            if (!service.existsById(id)) {
                    return ResponseEntity.notFound().build();
            }
            service.deleteById(id);
            return ResponseEntity.noContent().build();
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }
	
	
}  
