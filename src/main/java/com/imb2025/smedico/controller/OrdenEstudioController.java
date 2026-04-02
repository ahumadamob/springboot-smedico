package com.imb2025.smedico.controller;

import java.time.LocalDate; 
import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.imb2025.smedico.dto.ApiResponseSuccessDto;
import com.imb2025.smedico.dto.request.OrdenEstudioRequestDto;
import com.imb2025.smedico.dto.response.OrdenEstudioResponseDto;
import com.imb2025.smedico.entity.Medico;
import com.imb2025.smedico.entity.OrdenEstudio;
import com.imb2025.smedico.mapper.OrdenEstudioMapper;
import com.imb2025.smedico.repository.MedicoRepository;
import com.imb2025.smedico.service.IOrdenEstudioService;
import utilities.EstadoOrden;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/ordenestudio")
public class OrdenEstudioController {

    @Autowired
    private IOrdenEstudioService service;
    
    @Autowired
    private MedicoRepository medicoRepository;
    
    @Autowired
    private OrdenEstudioMapper mapper;


    // GET - Obtener todas las órdenes de estudio
    @GetMapping
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> findAllOrdenEstudio() {
        List<OrdenEstudio> ordenes = service.findAll();
        List<OrdenEstudioResponseDto> listaResponse = new ArrayList<OrdenEstudioResponseDto>();
    	OrdenEstudioMapper mapper=new OrdenEstudioMapper();

        for(OrdenEstudio o: ordenes){
        	OrdenEstudioResponseDto dto = new OrdenEstudioResponseDto();
         	dto=mapper.toDto(o);
         	listaResponse.add(dto);
         	
        }
        
        String message;
        if (ordenes.size() == 0) {
            message = "No hay órdenes de estudio disponibles";
        } else {
            message = "Lista de órdenes de estudio";
        }

        ApiResponseSuccessDto<List<OrdenEstudioResponseDto>> resp =
                new ApiResponseSuccessDto<>(true, message, listaResponse);

        return ResponseEntity.ok(resp);
    }

    // GET - Obtener una orden de estudio por ID
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> findOrdenEstudioById(@PathVariable("id") Long id) {
 
        OrdenEstudio orden = service.findById(id);
        OrdenEstudioMapper mapper=new OrdenEstudioMapper();
    	OrdenEstudioResponseDto dto= new OrdenEstudioResponseDto();
        dto=mapper.toDto(orden); 
        ApiResponseSuccessDto<OrdenEstudioResponseDto> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio encontrada", dto);
        return ResponseEntity.ok(resp);
    }


 // GET - Obtener órdenes de estudio por fecha
    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudio>>> findOrdenEstudioByFecha(@PathVariable LocalDate fecha) {
        List<OrdenEstudio> ordenes = service.findByFecha(fecha);
        ApiResponseSuccessDto<List<OrdenEstudio>> resp = new ApiResponseSuccessDto<>(true, "Órdenes encontradas", ordenes);
        return ResponseEntity.ok(resp);
    }

    // GET - Obtener la cantidad de órdenes por médico
    @GetMapping("/medico/{idMedico}/count")
    public ResponseEntity<ApiResponseSuccessDto<Long>> countByMedico(@PathVariable Long idMedico) {
        Medico medico = medicoRepository.findById(idMedico)
            .orElseThrow(() -> new RuntimeException("Médico no encontrado"));

        long cantidad = service.countByMedico(medico);
        ApiResponseSuccessDto<Long> resp = new ApiResponseSuccessDto<>(true, "Cantidad encontrada", cantidad);
        return ResponseEntity.ok(resp);
    }

    
    // GET Listado True: registros donde el atributo sea true.
    @GetMapping("/orden/autorizado/true")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getAutorizadoTrue(){
    	List<OrdenEstudio> orden=service.findByAutorizadoTrue();
    	List<OrdenEstudioResponseDto> lista=mapper.toResponseDtoList(orden);
    	
    	return ResponseEntity.ok().body(new ApiResponseSuccessDto<>(true,"Lista de verdaderos autorizados: ",lista));
    }
    
   // GET Listado False: registros donde el atributo sea false.
    @GetMapping("/orden/autorizado/false")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getAutorizadoFalse(){
    	List<OrdenEstudio> orden=service.findByAutorizadoFalse();
    	List<OrdenEstudioResponseDto> lista=mapper.toResponseDtoList(orden);
    	
    	return ResponseEntity.ok().body(new ApiResponseSuccessDto<>(true,"Lista de falsos autorizados: ",lista));
    }
    
    // GET - Obtener la cantidad de órdenes por médico
    @GetMapping("/recurso/vigentes")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getVigentes() {
        List<OrdenEstudio> ordenes = service.findByFechaVigenciaGreaterThanEqual(LocalDate.now());
        List<OrdenEstudioResponseDto> lista = mapper.toResponseDtoList(ordenes);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Órdenes de estudio vigentes", lista));

    }

    // GET - Órdenes vencidas
    @GetMapping("/recurso/vencidos")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> getVencidos() {
        List<OrdenEstudio> ordenes = service.findByFechaVigenciaLessThan(LocalDate.now());
        List<OrdenEstudioResponseDto> lista = mapper.toResponseDtoList(ordenes);
        return ResponseEntity.ok(new ApiResponseSuccessDto<>(true, "Órdenes de estudio vencidas", lista));
    }

    // GET /recurso/stats/activos - { "total": <count> }
    @GetMapping("/recurso/stats/activos")
    public ResponseEntity<ApiResponseSuccessDto<Long>> getRecursosActivos(){
    	long orden=service.countByEstado(EstadoOrden.ACTIVA);
    	
    	return ResponseEntity.ok().body(new ApiResponseSuccessDto<>(true,"Recursos activos: ",orden));
    }
    
    // GET /recurso/stats/inactivos - { "total": <count> }
    @GetMapping("/recurso/stats/inactivos")
    public ResponseEntity<ApiResponseSuccessDto<Long>> getRecursosInactivos(){
    	long orden=service.countByEstado(EstadoOrden.INACTIVA);
    	
    	return ResponseEntity.ok().body(new ApiResponseSuccessDto<>(true,"Recursos inactivos: ",orden));
    }
    
    // GET /recurso/alta-prioridad
    @GetMapping("/recurso/alta-prioridad")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> mostrarPrioridadMayorQue(@PathVariable int prioridad){
    	List<OrdenEstudio> orden= service.mostrarPrioridadMayorQue(prioridad);  
    	List<OrdenEstudioResponseDto> lista=mapper.toResponseDtoList(orden);
    	
    	return ResponseEntity.ok().body(new ApiResponseSuccessDto<>(true,"Altas prioridades: ",lista));
      
    }

    // GET /recurso/baja-prioridad
    @GetMapping("/recurso/baja-prioridad")
    public ResponseEntity<ApiResponseSuccessDto<List<OrdenEstudioResponseDto>>> mostrarPrioridadMenorQue(@PathVariable int prioridad){
    	List<OrdenEstudio> orden= service.mostrarPrioridadMenorQue(prioridad);
    	    	
    	List<OrdenEstudioResponseDto> lista=mapper.toResponseDtoList(orden);
    	
    	return ResponseEntity.ok().body(new ApiResponseSuccessDto<>(true,"Bajas prioridades: ",lista));

    }
    
    // POST - Crear una nueva orden de estudio
    @PostMapping
    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> createOrdenEstudio(@Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {
          
    	
    	
    	
    	
    	OrdenEstudio orden=mapper.fromDto(dto);
    	int prioridad=orden.getPrioridad();
    	if (prioridad >= 6 ) {
			 throw new BadRequestException("Prioridad entre 1 y 5 (inclusive)");

		}else {
			
    	 OrdenEstudio guardada = service.create(mapper.fromDto(dto));
         return ResponseEntity.status(HttpStatus.CREATED)
                 .body(new ApiResponseSuccessDto<>(true, "Orden creada correctamente", mapper.toDto(guardada)));
		}
    }





    // PUT - Actualizar una orden de estudio
    @PutMapping("/{id}")

    public ResponseEntity<ApiResponseSuccessDto<OrdenEstudioResponseDto>> updateOrdenEstudio(@PathVariable("id") Long id, @Valid @RequestBody OrdenEstudioRequestDto dto) throws Exception {
        
          OrdenEstudioResponseDto actualizada = service.update(id, dto);

          ApiResponseSuccessDto<OrdenEstudioResponseDto> resp = new ApiResponseSuccessDto<>(true, "Orden de Estudio actualizada", actualizada);
          return ResponseEntity.ok(resp);
          

    }

    // DELETE - Eliminar una orden de estudio
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseSuccessDto<String>> deleteOrdenEstudio(@PathVariable Long id) {
        service.deleteById(id);

        ApiResponseSuccessDto<String> resp =
                new ApiResponseSuccessDto<>(true, "Orden de Estudio eliminada correctamente", "Id " + id);

        return ResponseEntity.ok(resp);
    }
}
