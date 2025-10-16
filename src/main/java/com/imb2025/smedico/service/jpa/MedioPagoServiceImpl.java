package com.imb2025.smedico.service.jpa;

//import com.imb2025.smedico.dto.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.entity.MedioPago.TipoPago;
import com.imb2025.smedico.exception.ResourceNotFoundException;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.service.IMedioPagoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MedioPagoServiceImpl implements IMedioPagoService {
        @Autowired
        private MedioPagoRepository repo;

        @Override
        public List<MedioPago> findAll() {
                return repo.findAll(); 
        }


	@Override
        public MedioPago findById(Long id) {
                	return repo.findById(id)
                		    .orElseThrow(() -> new ResourceNotFoundException(
                		        "Entidad no encontrada, id: " + id)); 
        }

   @Override
   public boolean existsById(Long id) {
          return repo.existsById(id);
   }

	@Override
        public MedioPago create(MedioPago medioPago) {
                return repo.save(medioPago);
        }

	@Override
        public MedioPago update(Long id, MedioPago medioPago) {
                if (repo.existsById(id)) {
                        medioPago.setId(id);
                        return repo.save(medioPago);
                }
                return repo.findById(id)//c
            		    .orElseThrow(() -> new ResourceNotFoundException(
            		        "No se pudo actualizar. Entidad no encontrada de id " + id)); 
        } 

	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}

    /*@Override
    public MedioPago fromDto(MedioPagoRequestDto dto) {
    	MedioPago medioPago = new MedioPago();
    	medioPago.setNombre(dto.getNombre());
    	medioPago.setTipo(dto.getTipo());     
    	return medioPago; 
    }*/
    
    //a
    @Override
    public List<MedioPago> findByTipo(TipoPago tipo) {
    	return repo.findByTipo(tipo);
    }

    //a
    @Override
    	public Long countByNombre(String nombre) {
    	return repo.countByNombre(nombre);
    }
 
    
}