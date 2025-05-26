package com.imb2025.smedico.service;

import java.util.List;
import com.imb2025.smedico.entity.Consultorio;

public interface IConsultorioService {
	
	//Crear
	public Consultorio guardarConsultorio(Consultorio consultorio);
	//Listar
	public List<Consultorio> listarTodos();
	//Eliminar por id
	public void eliminarPorId(Long id);
	//Eliminar por nombre
	public void eliminarPorNombre(String Nombre);
	//Buscar por id
	public Consultorio buscarPorId(Long id);
	//Buscar por nombre
	public List<Consultorio> buscarPorNombre(String nombre);
	
}
