package com.imb2025.smedico.service.jpa;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.dto.request.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.service.IMedioPagoService;


@Service
public class MedioPagoServiceImpl implements IMedioPagoService {

	@Autowired
	private MedioPagoRepository repo;
	
	@Override
	public List<MedioPago> findAll(){
		return repo.findAll();
	}
	
	@Override
	public MedioPago findById(Long id) {
		return repo.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("El registro de Id "+ id +" No ah sido encontrado."));
		//Optional <MedioPago> optMp= repo.findById(id);
		/*if(optMp.isPresent()) {
			return optMp.get();
		}else {
			return null;
		}*/
	}
	
	@Override
	public MedioPago save(MedioPago mediopago) {
		return repo.save(mediopago); //llame al metodo de crudrepository() no al mas completo de jparepository porque ahora solo necesito las operaciones basicas
	}
	
	@Override
	public MedioPago update(Long id, MedioPago mediopago) throws Exception {
	
		if(repo.existsById(id)){
			mediopago.setId(id);
			return repo.save(mediopago);
		}else {
			throw new Exception("Medio de pago de id: "+ id +" No encontrado!");
		}
	}	
	
	@Override
	public void deleteById(Long id) throws Exception{
		if(repo.existsById(id)) {
			repo.deleteById(id);
		}else {
			throw new Exception ("Registro de id "+ id +" No encontrado.");
		}
	}

	//este el conocido fromDtro
	@Override
	public MedioPago crearMedioPago(MedioPagoRequestDto dtor) throws Exception{ 
		
		if(dtor.getNombre() == null || dtor.getNombre().isBlank() ) {
			throw new IllegalArgumentException("El campo Nombre esta vacio. Ingrese un valor");
			
		}if(dtor.getTipo() == null || dtor.getTipo().toString().isBlank()) {
			throw new IllegalArgumentException("El campo Tipo esta vacio. Ingrese un valor");
		}
		MedioPago mediopago = new MedioPago();
		mediopago.setNombre(dtor.getNombre());
		mediopago.setTipo(dtor.getTipo());
		
		return mediopago; 
	}
	
	public boolean existById (Long id) {
		return repo.existsById(id);
	}

	@Override
	public List<MedioPago> findByTipo(TipoPago tipo) {
		return repo.findByTipo(tipo); 
	}
	
	@Override
	public Long countByNombre(String nombre) {
		return repo.countByNombre(nombre);
	}

	

}
