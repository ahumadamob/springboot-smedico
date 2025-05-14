package com.imb2025.smedico.ServicieET.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imb2025.smedico.EntityET.EstadoTurnoE;
import com.imb2025.smedico.RepositoryET.EstadoTurnoR;
import com.imb2025.smedico.ServicieET.EstadoTurnoS;



@Service

public class ImplementacionService implements EstadoTurnoS { //indicamos que la clase cumpla con lo que define la EstadoTurnoS

    private final EstadoTurnoR estadoTurnoRepository; //instanciamos EstadoTurnoR para acceder a sus metodos

    //inyectamos las dependencias 
    public ImplementacionService(EstadoTurnoR estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    @Override 
    public List<EstadoTurnoE> findAll() {  //llamamos al metodo FinAll de la interfaz para que devuelva los registros
        return estadoTurnoRepository.findAll();
    }

    @Override
    public Optional<EstadoTurnoE> findById(Long id) { // Busca y devuelve el nombre EstadoTurnoE que puede estar vacio o no
        return estadoTurnoRepository.findById(id);
    }

    @Override
    public EstadoTurnoE save(EstadoTurnoE estadoTurno) { //guarda el objeto recbido, lo cual si tiene id lo actualizay si no lo crea
        return estadoTurnoRepository.save(estadoTurno);
    }

    @Override
    public EstadoTurnoE update(Long id, EstadoTurnoE estadoTurno) {
        Optional<EstadoTurnoE> existente = estadoTurnoRepository.findById(id);

        if (existente.isPresent()) {
            EstadoTurnoE actualizado = existente.get();
            
            // 🔄 Asegurarse de que se actualiza el nombre antes de guardar
            actualizado.setNombre(estadoTurno.getNombre());

            return estadoTurnoRepository.save(actualizado);
        } else {
            throw new RuntimeException("EstadoTurno con id " + id + " no encontrado");
        }
    }

    @Override
    public void deleteById(Long id) { //eliminamos un registro segun su ID
        estadoTurnoRepository.deleteById(id);
    }
}




