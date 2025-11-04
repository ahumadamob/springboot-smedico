package com.imb2025.smedico.controller;

import java.util.ArrayList;
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

import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.dto.response.MedioPagoResponseDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;
import com.imb2025.smedico.mapper.MedioPagoMapper;
import com.imb2025.smedico.service.IMedioPagoService;

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
    public ResponseEntity<ApiResponseSuccessDto<List<MedioPagoResponseDto>>> findAllMedioPago() {
    	List<MedioPago> lista = service.findAll();
        List<MedioPagoResponseDto> listaResponseDto = new ArrayList<MedioPagoResponseDto>();
        MedioPagoMapper medioPagoMapper = new MedioPagoMapper();
    	
        
        for(MedioPago mediopago: lista) {
        	MedioPagoResponseDto respDto = new MedioPagoResponseDto();
        	respDto = medioPagoMapper.toResponseDto(mediopago);
        	listaResponseDto.add(respDto);
        }
            

        ApiResponseSuccessDto<List<MedioPagoResponseDto>> response =
            new ApiResponseSuccessDto<>(true, listaResponseDto.isEmpty() ? "No hay registro de medios de pago" : "Todos los registros de medios de pago", listaResponseDto);    
        return ResponseEntity.ok(response);

    }

    @GetMapping("/{idmediopago}")
    public ResponseEntity<ApiResponseSuccessDto<MedioPagoResponseDto>> findMedioPagoByid(@PathVariable("idmediopago") Long id) {
        MedioPago medioPago = service.findById(id);
        MedioPagoMapper mapper = new MedioPagoMapper();
        MedioPagoResponseDto respDto = mapper.toResponseDto(medioPago);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Medio de pago de id " + id + " encontrado", respDto));
    }


    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<MedioPagoResponseDto>> createMedioPago(@Valid @RequestBody MedioPagoRequestDto mediopagoRequestDto) { //corregido
    	MedioPagoMapper mapper = new MedioPagoMapper();
		MedioPago creado = service.create(mapper.fromDto(mediopagoRequestDto));
        MedioPagoResponseDto responseDto = mapper.toResponseDto(creado);
        ApiResponseSuccessDto<MedioPagoResponseDto> response = new ApiResponseSuccessDto<>(true, "Medio de pago creado exitosamente!", responseDto);
        
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<MedioPagoResponseDto>> updateMedioPago(@PathVariable Long id, @Valid @RequestBody MedioPagoRequestDto mediopagoDto) { //corregido
    	MedioPagoMapper mapper = new MedioPagoMapper();
        MedioPago medioPago = mapper.fromDto(mediopagoDto);
        medioPago.setId(id); 
        MedioPago actualizado = service.update(id, medioPago);
        MedioPagoResponseDto responseDto = mapper.toResponseDto(actualizado);
        ApiResponseSuccessDto<MedioPagoResponseDto> response = new ApiResponseSuccessDto<>(true, "Medio de pago actualizado exitosamente!", responseDto);
        return ResponseEntity.ok(response);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteMedioPago(@PathVariable Long id) {
            service.deleteById(id); 
            ApiResponseSuccessDto<String> response = new ApiResponseSuccessDto<>(true, "Medio de pago eliminado exitosamente!", "id: "+ id);
            return ResponseEntity.ok(response);
    }
    
    
    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<ApiResponseSuccessDto<List<MedioPagoResponseDto>>> findByTipo(@PathVariable String tipo) { //corregido
        TipoPago tipoEnum = TipoPago.valueOf(tipo.toUpperCase());
        List<MedioPago> lista = service.findByTipo(tipoEnum);
		List<MedioPagoResponseDto> listaResponseDto = new ArrayList<>();//a
		MedioPagoMapper mapper = new MedioPagoMapper();
		
		for (MedioPago mediopago : lista) {
            listaResponseDto.add(mapper.toResponseDto(mediopago));
        }
        String mensaje = listaResponseDto.isEmpty()
            ? "No se encontraron medios de pago del tipo " + tipo
            : "Medios de pago del tipo " + tipo;
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, mensaje, listaResponseDto));
    }


    
    @GetMapping("/count/{nombre}")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByNombre(@PathVariable String nombre) {
        Long cantidad = service.countByNombre(nombre);
        String mensaje = "Cantidad de medios de pago con nombre '" + nombre + "': " + cantidad;
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, mensaje, cantidad));
    }



}  
