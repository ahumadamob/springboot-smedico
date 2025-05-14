package com.imb2025.smedico.ServicieET;

import com.imb2025.smedico.EntityET.EstadoTurnoE;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
@Service

public interface EstadoTurnoS {
	public List<EstadoTurnoE> findAll(); //devuelve todos los registros de la tabla de bd

    public Optional<EstadoTurnoE> findById(Long id);// Optional se utiliza para devolver el "EstadoTurno" o en caso de estar vacio "Optional.empty()", eso es para evitar un NullException 

    public EstadoTurnoE save(EstadoTurnoE estadoTurno); // guarda un nuevo estado de turno

    public EstadoTurnoE update(Long id, EstadoTurnoE estadoTurno); // actualiza un estado segun su id

   public  void deleteById(Long id); // elimina un registro
	

}
