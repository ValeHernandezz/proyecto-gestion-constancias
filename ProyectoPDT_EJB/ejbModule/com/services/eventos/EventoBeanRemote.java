package com.services.eventos;

import java.util.ArrayList;

import javax.ejb.Remote;

import com.exception.ServiciosException;

import com.entities.Evento;
import com.entities.Tutor;

@Remote
public interface EventoBeanRemote {

	Evento crearEvento(Evento evento) throws ServiciosException;

	boolean actualizarEvento(Evento evento) throws ServiciosException;

	boolean borrarEvento(Long idEvento) throws ServiciosException;

	ArrayList<Evento> listarEventos() throws ServiciosException;

	ArrayList<Evento> listarEventosFiltro(String filtro) throws ServiciosException;
	
	Evento buscarEventoPorId(Long id) throws ServiciosException;
	
	boolean asignarTutorAEvento(Evento oEvento, Tutor oTutor);

}
