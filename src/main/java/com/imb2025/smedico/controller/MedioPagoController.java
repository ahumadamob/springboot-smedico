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

import com.imb2025.smedico.dto.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.service.IMedioPagoService;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;

import jakarta.validation.Valid;

import com.imb2025.smedico.dto.ApiResponseSuccessDto;

@RestController
@RequestMapping("/mediopago")
public class MedioPagoController {

	@Autowired
	private final IMedioPagoService service;

	public MedioPagoController(IMedioPagoService service) {
	    this.service = service;
	}

    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<MedioPago>>> findAllMedioPago() {
    	List<MedioPago> lista = service.findAll();

    	ApiResponseSuccessDto<List<MedioPago>> response; 

    	if (lista.isEmpty()) {
    		response = new ApiResponseSuccessDto<>(true,"No hay registro de medios de pago",lista);
    		
        }else {
        	response = new ApiResponseSuccessDto<>(true,"Todos los registros de medios de pago",lista);
    	}
    	return ResponseEntity.ok(response);
    }

    @GetMapping("/{idmediopago}")
    public ResponseEntity<ApiResponseSuccessDto<MedioPago>> findMedioPagoByid(@PathVariable("idmediopago") Long id) {
            MedioPago medioPago = service.findById(id); 
            ApiResponseSuccessDto<MedioPago> response = new ApiResponseSuccessDto<>(true, "Medio de pago de id "+ id + " encontrado", medioPago);
            return ResponseEntity.ok(response);
    }

    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<MedioPago>> createMedioPago(@RequestBody MedioPagoRequestDto mediopagoRequestDto) throws Exception { 

            MedioPago medioPago = service.fromDto(mediopagoRequestDto);
            MedioPago creado = service.create(medioPago);
            ApiResponseSuccessDto<MedioPago> response = new ApiResponseSuccessDto<>(true, "Medio de pago creado exitosamente!", medioPago);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
    } 

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<MedioPago>> updateMedioPago(@PathVariable Long id, @RequestBody MedioPagoRequestDto mediopagoDto) throws Exception { 

            MedioPago medioPago = service.fromDto(mediopagoDto);
            MedioPago actualizado = service.update(id, medioPago); 
            ApiResponseSuccessDto<MedioPago> response = new ApiResponseSuccessDto<>(true,"Medio de pago actualizado exitosamente!", actualizado);
    	    return ResponseEntity.ok(response); 
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteMedioPago(@PathVariable Long id) throws Exception {
            service.deleteById(id); 
            ApiResponseSuccessDto<String> response = new ApiResponseSuccessDto<>(true, "Medio de pago eliminado exitosamente!", "id: "+ id);
            return ResponseEntity.ok(response);
    }

}  