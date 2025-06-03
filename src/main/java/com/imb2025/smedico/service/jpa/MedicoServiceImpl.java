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
        if (dto.getNombre() == null || dto.getNombre().isBlank()) {
            throw new IllegalArgumentException("Nombre no pueden ser nulo");
        }
        
        if (dto.getApellido() == null || dto.getApellido().isBlank()) {
            throw new IllegalArgumentException("Apellido no pueden ser nulo");
        }
            
        if (dto.getEspecialidad() == null || dto.getEspecialidad().isBlank()) {
        	throw new IllegalArgumentException("Especialidad no pueden ser nula");
        }
        
        if (dto.getMatricula() == null || dto.getMatricula().isBlank()) {
            throw new IllegalArgumentException("La Matricula no pueden ser nula");
        }
        
        if (dto.getEmail() == null || dto.getEmail().isBlank()) {
            throw new IllegalArgumentException("El Email no pueden ser nulo");
        }
        
        if (dto.getTelefono() == null || dto.getTelefono().isBlank()) {
            throw new IllegalArgumentException("El telefono no pueden ser nulo");
        }
        
        Medico medico = new Medico();
        medico.setNombre(dto.getNombre());
        medico.setApellido(dto.getApellido());
        medico.setEmail(dto.getEmail());
        medico.setMatricula(dto.getMatricula());
        medico.setEspecialidad(dto.getEspecialidad());
        medico.setTelefono(dto.getTelefono());
        

        return  medico;
    }

	


    }


