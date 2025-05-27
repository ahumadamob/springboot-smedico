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
        Optional<Medico> 
        opt = repo.findById(id);
        if(opt.isPresent()) {
			return opt.get();
		}else {
			return null;
		}
    }


    @Override
    public void deleteById(Long id) {
        repo.deleteById(id);
    }


	@Override
	public Medico create(MedicoRequestDTO dto) {
		Medico medico = fromDto(dto);
        return repo.save(medico);
	}

	@Override
	public Medico update(Long id, MedicoRequestDTO dto) throws Exception {
		 Optional<Medico> opt = repo.findById(id);
		    if (opt.isPresent()) {
		        Medico medico = opt.get();
		        medico.setApellido(dto.getApellido());
			    medico.setNombre(dto.getNombre());
			    medico.setEmail(dto.getEmail());
			    medico.setEspecialidad(dto.getEspecialidad());
			    medico.setMatricula(dto.getMatricula());
			    medico.setTelefono(dto.getTelefono());
		        return repo.save(medico);
		    } else {
		        throw new Exception("Medico con ID " + id + " no existe.");
	}
	}
	
	
	
	private Medico fromDto(MedicoRequestDTO dto) {
	    Medico medico = new Medico();
	    medico.setApellido(dto.getApellido());
	    medico.setNombre(dto.getNombre());
	    medico.setEmail(dto.getEmail());
	    medico.setEspecialidad(dto.getEspecialidad());
	    medico.setMatricula(dto.getMatricula());
	    medico.setTelefono(dto.getTelefono());

	    return medico;
	}

	


}

