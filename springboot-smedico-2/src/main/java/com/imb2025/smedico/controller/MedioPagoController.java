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

import com.imb2025.smedico.dto.MedioPagoRequestDTO;
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
	
	@GetMapping //("/")
	public ResponseEntity<List<MedioPago>>findAllMedioPago(){
		List<MedioPago> mediopago = service.findAll();
		 if(mediopago.isEmpty()){
	            return ResponseEntity.noContent().build();
	        }
		return ResponseEntity.ok(mediopago); 
	}
	
	@GetMapping("/{idmediopago}")
	public ResponseEntity<MedioPago> findMedioPagoByid(@PathVariable("idmediopago") Long id) { 
		MedioPago m = new MedioPago();
		m = service.findById(id);
		if (m == null) { 
			return ResponseEntity.notFound().build();
		}else {
			//return ResponseEntity.ok(service.findById(id));
			return ResponseEntity.ok(m);
		}
	}
	
	@PostMapping ("/")
    public ResponseEntity <MedioPago> createMedioPago(@RequestBody MedioPagoRequestDTO dto) throws Exception{
			return ResponseEntity.ok(service.save(service.fromDto(dto)));
    }
	
	@PutMapping ("/{id}")
	public ResponseEntity<MedioPago> updateMedioPago(@PathVariable("id") Long id, @RequestBody MedioPagoRequestDTO dto) throws Exception{
		MedioPago m = service.fromDto(dto);
		MedioPago actualiz= service.update(id, m);
		return ResponseEntity.ok(actualiz);
		
			//return ResponseEntity.ok(service.update(id, service.fromDto(dto)));
		
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteMedioPago(@PathVariable Long id) throws Exception{
		service.deleteById(id); 
		return ResponseEntity.ok("El medio de pago de ID: " + id + " fue eliminado con éxito.");
	}	
		
		
	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleGlobalExceptions(Exception ex){
		return ResponseEntity.badRequest().body(ex.getMessage());
	}
}  
