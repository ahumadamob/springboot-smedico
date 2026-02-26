package com.imb2025.smedico.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;
import com.imb2025.smedico.service.IMedioPagoService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/mediopago")
public class MedioPagoController {
	
	@Autowired
	private IMedioPagoService service;

    MedioPagoController() {
    } 
    
    
	@GetMapping
	public ResponseEntity<ApiResponseSuccessDto<List<MedioPago>>> findAll() {
		List <MedioPago> mediopago = service.findAll();
		ApiResponseSuccessDto <List<MedioPago>> respuesta;
		if (mediopago.isEmpty()){
			respuesta = new ApiResponseSuccessDto <>(true,"La tabla MediosDePago se encuentra vacia",mediopago);
			//return ResponseEntity.noContent().build();
		}else {	
			respuesta = new ApiResponseSuccessDto<>(true,"Lista de MediosDePago encontrada!",mediopago);
		}
		return ResponseEntity.ok(respuesta);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<MedioPago>> findById(@PathVariable Long id) {
		MedioPago mediopago = service.findById(id);
		ApiResponseSuccessDto <MedioPago> respuesta = new ApiResponseSuccessDto <>(true,"MedioPago de Id "+ id +" encontrado!",mediopago);
		return ResponseEntity.ok(respuesta);
		/*if (mediopago == null) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(mediopago);
		}*/
	}
	
	@PostMapping
	public ResponseEntity<ApiResponseSuccessDto<MedioPago>> create(@Valid @RequestBody MedioPagoRequestDto dtor) throws Exception{ 
		MedioPago mediopago = service.save(service.crearMedioPago(dtor));
		ApiResponseSuccessDto <MedioPago> respuesta = new ApiResponseSuccessDto<>(true,"nuevo registro MedioPago creado exitosamente!",mediopago);
		return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
	} 
	
	@PutMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<MedioPago>> update(@PathVariable Long id, @Valid @RequestBody MedioPagoRequestDto dtor) throws Exception{
		MedioPago mediopago = service.update(id, service.crearMedioPago(dtor));
		ApiResponseSuccessDto <MedioPago> respuesta = new ApiResponseSuccessDto <>(true,"Registro MedioPago de id "+ id +" actualizado exitosamente!",mediopago);
		return ResponseEntity.ok(respuesta); 
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ApiResponseSuccessDto<String>> deleteById(@PathVariable Long id) throws Exception{
		service.deleteById(id); 
		ApiResponseSuccessDto<String> mensaje = new ApiResponseSuccessDto<>(true,"MedioPago de id "+ id +" eliminado exitosamente!","ID "+ id);
		return ResponseEntity.ok(mensaje);
	}

	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<ApiResponseSuccessDto<List<MedioPago>>> findByTipo(@PathVariable TipoPago tipo){
		List<MedioPago> mediopagoPorNombre= service.findByTipo(tipo);
		ApiResponseSuccessDto <List<MedioPago>> respuesta;
		if (mediopagoPorNombre.isEmpty()){
			respuesta = new ApiResponseSuccessDto <>(true,"La tabla MediosDePago se encuentra vacia",mediopagoPorNombre);
			//return ResponseEntity.noContent().build();
		}else {	
			respuesta = new ApiResponseSuccessDto<>(true,"Lista de MediosDePago por Tipo "+ tipo +" encontrada!",mediopagoPorNombre);
		}
		return ResponseEntity.ok(respuesta);
	}
	
	@GetMapping("/nombre/{nombre}")
	public ResponseEntity<ApiResponseSuccessDto<Long>> countByNombre (@PathVariable String nombre){
		Long CantidadPorNombre= service.countByNombre(nombre);
		ApiResponseSuccessDto<Long> respuesta = new ApiResponseSuccessDto <>(true,"Cantidad de MediosDePago filtrados por "+ nombre, CantidadPorNombre);
		return ResponseEntity.ok(respuesta);
	}
	
	
	
	/*
	@ExceptionHandler (Exception.class)
	public ResponseEntity<String> handleExceptionDe400(Exception except){
		//except.printStackTrace();
		return ResponseEntity.badRequest().body(except.getMessage());
	}*/

}
