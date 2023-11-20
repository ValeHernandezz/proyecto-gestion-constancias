package com.services.eventos.eventoEstudiante;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.entities.EventoEstudiante;
import com.exception.ServiciosException;

@Remote
public interface EventoEstudianteBeanRemote {

	boolean crearEventoEstudiante(EventoEstudiante oEventoEstudiante) throws ServiciosException;

	boolean actualizarEventoEstudiante(EventoEstudiante oEventoEstudiante) throws ServiciosException;

	boolean eliminarEventoEstudiante(Long idEventoEstudiante) throws ServiciosException;

	ArrayList<EventoEstudiante> listarEventosEstudiante() throws ServiciosException;

	ArrayList<EventoEstudiante> listarEventosEstudiante(String filtro) throws ServiciosException;
}
