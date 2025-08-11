package com.imb2025.smedico.service.jpa;

import com.imb2025.smedico.dto.MedioPagoRequestDto;
import com.imb2025.smedico.entity.MedioPago;
import com.imb2025.smedico.repository.MedioPagoRepository;
import com.imb2025.smedico.service.IMedioPagoService;
import java.util.List;
import java.util.Optional;
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
		Optional<MedioPago> opt;
		opt = repo.findById(id);
		if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
	}

	@Override
        public MedioPago create(MedioPago medioPago) {
                return repo.save(medioPago);
        }
	
	@Override
        public MedioPago update(MedioPago medioPago, Long id) {
                if (repo.existsById(id)) {
                        medioPago.setId(id);
                        return repo.save(medioPago);
                }
                return null;
        }
	
	@Override
	public void deleteById(Long id) {
		repo.deleteById(id);
	}
	
        @Override
        public MedioPago fromDto(MedioPagoRequestDto dto) {
                MedioPago medioPago = new MedioPago();
                medioPago.setNombre(dto.getNombre());
                medioPago.setTipo(dto.getTipo());
                return medioPago;
        }
}
