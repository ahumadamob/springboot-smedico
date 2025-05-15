package com.imb2025.smedico.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.imb2025.smedico.entity.EstadoTurno;
import com.imb2025.smedico.repository.EstadoTurnoRepository;
import com.imb2025.smedico.service.IEstadoTurnoService;



@Service

public class EstadoTurnoImpl implements IEstadoTurnoService { //indicamos que la clase cumpla con lo que define la EstadoTurnoS

    private final EstadoTurnoRepository estadoTurnoRepository; //instanciamos EstadoTurnoR para acceder a sus metodos

    //inyectamos las dependencias 
    public EstadoTurnoImpl(EstadoTurnoRepository estadoTurnoRepository) {
        this.estadoTurnoRepository = estadoTurnoRepository;
    }

    @Override 
    public List<EstadoTurno> findAll() {  //llamamos al metodo FinAll de la interfaz para que devuelva los registros
        return estadoTurnoRepository.findAll();
    }

    @Override
    public EstadoTurno findById(Long id) { // Busca y devuelve el nombre EstadoTurnoE que puede estar vacio o no
        return estadoTurnoRepository.findById(id).orElse(null);
    }

    @Override
    public EstadoTurno save(EstadoTurno estadoTurno) { //guarda el objeto recbido, lo cual si tiene id lo actualizay si no lo crea
        return estadoTurnoRepository.save(estadoTurno);
    }

    @Override
    public EstadoTurno update(Long id, EstadoTurno estadoTurno) {
        Optional<EstadoTurno> existente = estadoTurnoRepository.findById(id);

        if (existente.isPresent()) {
            EstadoTurno actualizado = existente.get();
            
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




