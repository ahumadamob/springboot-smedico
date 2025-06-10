	package com.imb2025.smedico.service.jpa;
	
	import java.util.List;
	import java.util.Optional;
	
	import org.springframework.beans.factory.annotation.Autowired;
	import org.springframework.stereotype.Service;
	
	import com.imb2025.smedico.dto.MedicoRequestDTO;
	import com.imb2025.smedico.entity.Medico;
	import com.imb2025.smedico.repository.MedicoRepository;
	import com.imb2025.smedico.service.IMedicoService;
	
	@Service
	public class MedicoServiceImpl implements IMedicoService {
	
	    @Autowired
	    private MedicoRepository repo;
	
	    @Override
	    public List<Medico> findAll() {
	        return repo.findAll();
	    }
	
	    @Override
	    public Medico findById(Long id) {
	        Optional<Medico> opt = repo.findById(id);
	        return opt.orElse(null);
	    }
	
	    @Override
	    public void deleteById(Long id) {
	        repo.deleteById(id);
	    }
	
	    @Override
	    public Medico create(Medico medico) {
	        return repo.save(medico);
	    }
	
	    @Override
	    public Medico update(Long id, Medico medico) throws Exception {
	        if (repo.existsById(id)) {
	            medico.setId(id);
	            return repo.save(medico);
	        } else {
	            throw new Exception("No existe el médico con ID: " + id);
	        }
	    }
	
	    @Override
	    public Medico fromDto(MedicoRequestDTO dto) throws Exception {
	        return new Medico(
	            dto.getNombre(),
	            dto.getApellido(),
	            dto.getEmail(),
	            dto.getMatricula(),
	            dto.getEspecialidad(),
	            dto.getTelefono()
	        );
	    }
	}
