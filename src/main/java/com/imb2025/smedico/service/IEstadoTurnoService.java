package com.imb2025.smedico.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.imb2025.smedico.dto.EstadoTurnoDTO;
import com.imb2025.smedico.entity.EstadoTurno;
@Service

public interface IEstadoTurnoService {
	public List<EstadoTurno> findAll(); //devuelve todos los registros de la tabla de bd

    public EstadoTurno findById(Long id);// Optional se utiliza para devolver el "EstadoTurno" o en caso de estar vacio "Optional.empty()", eso es para evitar un NullException 

    public EstadoTurno create( EstadoTurno estadoTurno); // guarda un nuevo estado de turno

    public EstadoTurno update(Long id, EstadoTurno estadoTurno); // actualiza un estado segun su id

    public  void deleteById(Long id); // elimina un registro poe id
	

}
