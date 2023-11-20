package com.services.usuario.estudiante;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.exception.ServiciosException;

import com.entities.Estudiante;

@Remote
public interface EstudianteBeanRemote {

	boolean crearEstudiante(Estudiante estudiante) throws ServiciosException;

	boolean actualizarEstudiante(Estudiante estudiante) throws ServiciosException;

	boolean borrarEstudiante(Long idEstudiante) throws ServiciosException;

	ArrayList<Estudiante> listarEstudiantes() throws ServiciosException;

	ArrayList<Estudiante> listarEstudiantesFiltro(String filtro) throws ServiciosException;

}
